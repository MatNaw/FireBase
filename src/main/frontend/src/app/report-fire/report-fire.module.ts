import { NgModule } from '@angular/core';

import { SharedModule } from '@app/shared/shared.module';
import { ReportFireComponent } from '@app/report-fire/report-fire.component';
import { ReportFireRoutingModule } from '@app/report-fire/report-fire-routing.module';

@NgModule({
  declarations: [
    ReportFireComponent
  ],
  imports: [
    SharedModule,
    ReportFireRoutingModule
  ],
  providers: []
})
export class ReportFireModule { }
