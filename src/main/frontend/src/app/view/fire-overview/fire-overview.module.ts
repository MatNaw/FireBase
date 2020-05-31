import { NgModule } from '@angular/core';
import { AgmCoreModule } from '@agm/core';

import { environment } from '@env/environment';
import { SharedModule } from '@app/shared/shared.module';
import { FireOverviewComponent } from '@app/view/fire-overview/fire-overview.component';
import { FireOverviewRoutingModule } from '@app/view/fire-overview/fire-overview-routing.module';
import { ReportFireComponent } from '@app/view/fire-overview/report-fire/report-fire.component';
import { ConfirmReportFireComponent } from "@app/view/fire-overview/report-fire/confirm-report-fire/confirm-report-fire.component";
import { FireOverviewService } from '@app/view/fire-overview/fire-overview.service';

@NgModule({
  declarations: [
    FireOverviewComponent,
    ReportFireComponent,
    ConfirmReportFireComponent
  ],
  imports: [
    SharedModule,
    FireOverviewRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: environment.google.googleApiKey,
      libraries: ["places"]
    })
  ],
  providers: [
    FireOverviewService
  ]
})
export class FireOverviewModule { }
