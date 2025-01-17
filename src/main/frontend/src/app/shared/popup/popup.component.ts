import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'fire-popup',
  templateUrl: './popup.component.pug',
  styleUrls: ['./popup.component.scss']
})
export class PopupComponent implements OnInit {
  @Input() modal: NgbModalRef;
  @Input() message: string;
  @Output() closeModalEvent = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit() { }

  closeModal(accept: boolean) {
    this.closeModalEvent.emit(accept);
    this.modal.close();
  }

}
