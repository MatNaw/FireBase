import { Component, OnInit, ViewChild } from '@angular/core';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { FireModel } from '@app/view/fire-overview/models/fire.model';
import { FireOverviewService } from '@app/view/fire-overview/fire-overview.service';
import { ViewportUtil } from '@app/core/viewport.util';

@Component({
  selector: 'fire-fire-overview',
  templateUrl: './fire-overview.component.pug',
  styleUrls: ['./fire-overview.component.scss']
})
export class FireOverviewComponent implements OnInit {

  @ViewChild('reportFireContent') reportFireContent;
  public fires: FireModel[];
  isMobile: boolean;

  constructor(private fireOverviewService: FireOverviewService, private modals: NgbModal) {
    this.isMobile = ViewportUtil.isMobile();
  }

  ngOnInit() {
    this.getData();
  }

  reportFire() {
    this.modals.open(this.reportFireContent, { size: 'lg', centered: true, backdrop: 'static' });
  }

  onSubmittedEventEmitter() {
    this.getData();
  }

  getSquadsAmount(fireId: number) {
    return this.fires.find(fire => fire.id === fireId)
      .squads.map(it => it.squadAmount)
      .reduce((a, b) => a + b, 0);
  }

  private getData() {
    this.fireOverviewService.getAllActiveFires()
      .subscribe(result => this.fires = result);
  }
}
