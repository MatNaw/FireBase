import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'fire-report-fire',
  templateUrl: './fire-overview.component.pug',
  styleUrls: ['./fire-overview.component.scss']
})
export class FireOverviewComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    console.log('fire-overview');
  }

}
