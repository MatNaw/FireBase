import { NgModule } from '@angular/core';

import { SharedModule } from '@app/shared/shared.module';
import { FireOverviewComponent } from '@app/fire-overview/fire-overview.component';
import { FireOverviewRoutingModule } from '@app/fire-overview/fire-overview-routing.module';

@NgModule({
  declarations: [
    FireOverviewComponent
  ],
  imports: [
    SharedModule,
    FireOverviewRoutingModule
  ],
  providers: []
})
export class FireOverviewModule { }
