import {
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { MapsAPILoader } from '@agm/core';
import { FireOverviewService } from '@app/view/fire-overview/fire-overview.service';
import { BrigadeModel } from '@app/view/fire-overview/models/brigade.model';
import { SquadModel } from '@app/view/fire-overview/models/squad.model';

@Component({
  selector: 'fire-report-fire',
  templateUrl: './report-fire.component.pug',
  styleUrls: ['./report-fire.component.scss']
})
export class ReportFireComponent implements OnInit, OnDestroy {

  @Input() modal: NgbModalRef;
  @Output() submittedEventEmitter = new EventEmitter();

  private geoCoder;

  firePlace: String;
  numberOfSquads: number = 0;
  public squads: SquadModel[];
  lat: number = 52.230114;
  lng: number = 21.011244;
  zoom: number = 10;

  isCustomizeSquadsVisible = false;

  constructor(private fireOverviewService: FireOverviewService, private mapsAPILoader: MapsAPILoader) { }

  ngOnInit() {
    this.setupMapsAPI();
  }

  ngOnDestroy(): void {
    this.submittedEventEmitter.emit();
  }

  reportFire() {
    this.fireOverviewService.reportFire(this.lat, this.lng, this.numberOfSquads).subscribe(response => {
      this.squads = response;
      this.isCustomizeSquadsVisible = true;
    });
  }

  onSubmit() {
    this.fireOverviewService.acceptFire().subscribe();
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

  getBrigadeDescription(brigade: BrigadeModel) {
    return `${brigade.name.toUpperCase()}: ${brigade.postalCode}, ${brigade.city}, ${brigade.street}`;
  }

  maxSquadsAmount (squadAmount: number, availableSquads: number) {
    return Math.min(this.remainingNumberOfSquads() + squadAmount, availableSquads)
  }

  remainingNumberOfSquads() {
    const currentCount = this.squads
      .map(it => it.squadAmount)
      .reduce((a, b) => a + b);

    return this.numberOfSquads - currentCount;
  }
}
