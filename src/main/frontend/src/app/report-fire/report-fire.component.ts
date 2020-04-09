import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'fire-report-fire',
  templateUrl: './report-fire.component.pug',
  styleUrls: ['./report-fire.component.scss']
})
export class ReportFireComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    console.log('report-fire');
  }

}
