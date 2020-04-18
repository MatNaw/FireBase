import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'fire-fire-overview',
  templateUrl: './fire-overview.component.pug',
  styleUrls: ['./fire-overview.component.scss']
})
export class FireOverviewComponent implements OnInit {

  @ViewChild('reportFireContent') reportFireContent;

  constructor(private modals: NgbModal) { }

  ngOnInit() {
    console.log('fire-overview');
  }

  reportFire() {
    this.modals.open(this.reportFireContent, { size: 'lg', centered: true, backdrop: 'static' });
  }

  onSubmittedEventEmitter() {
    this.getData();
  }

  getData() {
    console.log('get active fires from db');
  }
}
