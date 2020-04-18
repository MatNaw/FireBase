import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'fire-report-fire',
  templateUrl: './report-fire.component.pug',
  styleUrls: ['./report-fire.component.scss']
})
export class ReportFireComponent implements OnInit, OnDestroy {

  @Input() modal: NgbModalRef;
  @Output() submittedEventEmitter = new EventEmitter();
  firePlace: String;

  lat: number = -23.8779431;
  lng: number = -49.8046873;
  zoom: number = 10;

  constructor() { }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.submittedEventEmitter.emit();
  }

  cancel() {
    this.modal.close();
  }

}
