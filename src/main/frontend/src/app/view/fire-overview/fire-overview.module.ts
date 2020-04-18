import { NgModule } from '@angular/core';

import { SharedModule } from '@app/shared/shared.module';
import { FireOverviewComponent } from '@app/view/fire-overview/fire-overview.component';
import { FireOverviewRoutingModule } from '@app/view/fire-overview/fire-overview-routing.module';
import { ReportFireComponent } from '@app/view/fire-overview/report-fire/report-fire.component';

@NgModule({
  declarations: [
    FireOverviewComponent,
    ReportFireComponent
  ],
  imports: [
    SharedModule,
    FireOverviewRoutingModule
  ],
  providers: []
})
export class FireOverviewModule { }
