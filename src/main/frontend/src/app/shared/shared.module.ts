import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatExpansionModule } from '@angular/material';

import { TranslateModule } from '@ngx-translate/core';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { NgSelectModule } from '@ng-select/ng-select';
import { TextMaskModule } from 'angular2-text-mask';

import { BootstrapModule } from '@app/shared/bootstrap.module';
import { ValidationErrorsComponent } from '@app/shared/validation/validation-errors.component';
import { ValidationErrorComponent } from '@app/shared/validation/validation-error.component';
import { PopupComponent } from '@app/shared/popup/popup.component';
import { CounterComponent } from '@app/shared/counter/counter.component';

export const components = [
  ValidationErrorsComponent,
  ValidationErrorComponent,
  PopupComponent,
  CounterComponent
];

@NgModule({
  declarations: [
    ...components
  ],
  imports: [
    CommonModule,
    FormsModule,
    TranslateModule,
    BootstrapModule,
    NgxDatatableModule,
    NgSelectModule,
    TextMaskModule,
    MatExpansionModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    TranslateModule,
    BootstrapModule,
    NgxDatatableModule,
    NgSelectModule,
    TextMaskModule,
    MatExpansionModule,

    ...components
  ]
})
export class SharedModule { }
