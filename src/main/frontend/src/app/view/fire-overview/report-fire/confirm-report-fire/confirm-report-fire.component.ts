import {
  Component,
  EventEmitter,
  Input,
  Output,
} from '@angular/core';
import {NgbModalRef} from "@ng-bootstrap/ng-bootstrap";


@Component({
  selector: 'fire-confirm-report-fire',
  templateUrl: './confirm-report-fire.component.pug',
  styleUrls: ['./confirm-report-fire.component.scss']
})
export class ConfirmReportFireComponent {

  @Input() modal: NgbModalRef;
  @Output() submittedEventEmitter = new EventEmitter();

  constructor() { }

  onSubmit() {
    this.modal.close();
  }

  cancel() {
    this.modal.dismiss();
  }
}
