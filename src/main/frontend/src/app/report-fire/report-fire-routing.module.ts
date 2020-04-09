import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ReportFireComponent } from '@app/report-fire/report-fire.component';

const routes: Routes = [
  { path: '',
    component: ReportFireComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportFireRoutingModule { }
