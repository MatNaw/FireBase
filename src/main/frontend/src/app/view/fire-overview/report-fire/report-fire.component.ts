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
