import {
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { MapsAPILoader } from '@agm/core';
import { FireOverviewService } from '@app/view/fire-overview/fire-overview.service';
import { SquadModel } from '@app/view/fire-overview/models/squad.model';
import { TranslateService } from "@ngx-translate/core";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: 'fire-report-fire',
  templateUrl: './report-fire.component.pug',
  styleUrls: ['./report-fire.component.scss']
})
export class ReportFireComponent implements OnInit, OnDestroy {

  @Input() modal: NgbModalRef;
  @Output() submittedEventEmitter = new EventEmitter();

  @ViewChild('confirmReportFire') confirmReportFire;

  private geoCoder;

  firePlace: String;
  numberOfSquads: number = 0;
  public squads: SquadModel[];
  lat: number = 52.230114;
  lng: number = 21.011244;
  zoom: number = 10;

  isCustomizeSquadsVisible = false;

  constructor(private toastr: ToastrService,
              private translationService: TranslateService,
              private modals: NgbModal,
              private mapsAPILoader: MapsAPILoader,
              private fireOverviewService: FireOverviewService) { }

  ngOnInit() {
    this.setupMapsAPI();
  }

  ngOnDestroy(): void {
    this.submittedEventEmitter.emit();
  }

  acceptFire() {
    this.fireOverviewService.acceptFire(this.firePlace, this.lat, this.lng, this.squads).subscribe(() => {
      this.toastr.success(this.translationService.instant('REPORT_FIRE.REQUEST_ACCEPTED'));
      this.cancel()
    });
  }

  reportFire() {
    this.fireOverviewService.reportFire(this.lat, this.lng).subscribe(response => {
      this.squads = response;
      this.squads.forEach(squad => squad.squadAmount = 0);
      this.isCustomizeSquadsVisible = true;
    });
  }

  onSubmit() {
    if(this.validateSumOfSquads() && this.validateEachSquadAmount()) {
      if (this.calculateCurrentSquadsCount() < this.numberOfSquads) {
        this.openConfirmationModal();
      }
      else {
        this.acceptFire();
      }
    }
  }

  cancel() {
    this.modal.close();
  }

  placeMarker(position) {
    this.lat = position.coords.lat;
    this.lng = position.coords.lng;
    this.setFirePlaceAddress(this.lat, this.lng);
  }

  private setupMapsAPI() {
    this.mapsAPILoader.load().then(() => {
      this.geoCoder = new google.maps.Geocoder;
      this.setCurrentLocation();

      let autocomplete = new google.maps.places.Autocomplete(<HTMLInputElement> document.getElementById('place'));
      autocomplete.setComponentRestrictions({ 'country': ['pl'] });
      autocomplete.setFields(['geometry', 'name', 'place_id']);

      autocomplete.addListener('place_changed', () => {
        let place = autocomplete.getPlace();
        if (place.geometry === undefined || place.geometry === null) {
          console.error('Geometry of this place was not found');
          return;
        } else if (place.place_id === undefined || place.place_id === null) {
          console.error('Place id was not found');
          return;
        }

        // set place coordinates
        this.lat = place.geometry.location.lat();
        this.lng = place.geometry.location.lng();
        this.setFirePlaceByPlaceId(place.place_id);
      });
    });
  }

  private setCurrentLocation() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
      });
    }
    this.setFirePlaceAddress(this.lat, this.lng);
  }

  private setFirePlaceAddress(latitude, longitude) {
    this.geoCoder.geocode({ 'location': { lat: latitude, lng: longitude } }, (results, status) => {
      if (status === 'OK') {
        if (results[0]) {
          this.firePlace = results[0].formatted_address;
        } else {
          console.error('No results found for latitude: %s and longitude: %s', latitude, longitude);
        }
      } else {
        console.error('GeoCoder failed, response status: %s' + status);
      }
    });
  }

  private setFirePlaceByPlaceId(placeId) {
    this.geoCoder.geocode({ 'placeId': placeId }, (results, status) => {
      if (status === 'OK') {
        if (results[0]) {
          this.firePlace = results[0].formatted_address;
        } else {
          console.error('No results found for this placeId: %s', placeId);
        }
      } else {
        console.error('GeoCoder failed, response status: %s' + status);
      }
    });
  }

  getBrigadeDescription(squad: SquadModel) {
    return `${squad.brigade.name.toUpperCase()}: ${squad.brigade.postalCode}, ${squad.brigade.city},
     ${squad.brigade.street}; ${this.translationService.instant('REPORT_FIRE.AVAILABLE_BRIGADES')}: ${squad.availableSquads}`;
  }

  maxSquadsAmount (squad: SquadModel) {
    return Math.min(this.remainingNumberOfSquads() + squad.squadAmount, squad.availableSquads)
  }

  calculateCurrentSquadsCount() {
    return this.squads
      .map(it => it.squadAmount)
      .reduce((a, b) => a + b);
  }

  remainingNumberOfSquads() {
    return this.numberOfSquads - this.calculateCurrentSquadsCount();
  }

  validateEachSquadAmount() {
    if(this.squads.filter(it => it.squadAmount > it.availableSquads).length > 0) {
      this.toastr.error(this.translationService.instant('VALIDATION.NUMBER_OF_SQUADS_INVALID'));
      return false;
    }
    return true;
  }

  validateSumOfSquads() {
    const counter = this.calculateCurrentSquadsCount();
    if (counter > this.numberOfSquads) {
      this.toastr.error(this.translationService.instant('VALIDATION.SUM_NUMBER_OF_SQUADS_INVALID'));
      return false;
    }
    else if (counter <= 0) {
      this.toastr.error(this.translationService.instant('VALIDATION.NO_SQUADS_CHOSEN'));
      return false;
    }
    return true;
  }

  openConfirmationModal() {
    this.modals.open(this.confirmReportFire, { centered: true, backdrop: 'static' })
      .result.then(() => {
        this.acceptFire();
    }, () => {
        this.toastr.info(this.translationService.instant('REPORT_FIRE.ADJUST_SQUADS'));
    });
  }
}
