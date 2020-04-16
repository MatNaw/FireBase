import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FireOverviewComponent } from '@app/fire-overview/fire-overview.component';

const routes: Routes = [
  { path: '',
    component: FireOverviewComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FireOverviewRoutingModule { }
