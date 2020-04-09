import { NgModule } from '@angular/core';

import { SharedModule } from '@app/shared/shared.module';
import { MainContainerComponent } from '@app/layout/main-container/main-container.component';
import { HeaderComponent } from '@app/layout/header/header.component';
import { MainRoutingModule } from '@app/main-routing.module';

@NgModule({
  declarations: [
    HeaderComponent,
    MainContainerComponent
  ],
  imports: [
    SharedModule,
    MainRoutingModule
  ]
})
export class MainModule { }
