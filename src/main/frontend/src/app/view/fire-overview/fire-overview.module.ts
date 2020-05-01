import { NgModule } from '@angular/core';
import { AgmCoreModule } from '@agm/core';

import { environment } from '@env/environment';
import { SharedModule } from '@app/shared/shared.module';
import { FireOverviewComponent } from '@app/view/fire-overview/fire-overview.component';
import { FireOverviewRoutingModule } from '@app/view/fire-overview/fire-overview-routing.module';
import { ReportFireComponent } from '@app/view/fire-overview/report-fire/report-fire.component';
import { FireOverviewService } from '@app/view/fire-overview/fire-overview.service';

@NgModule({
  declarations: [
    FireOverviewComponent,
    ReportFireComponent
  ],
  imports: [
    SharedModule,
    FireOverviewRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: environment.google.googleApiKey
    })
  ],
  providers: [
    FireOverviewService
  ]
})
export class FireOverviewModule { }
