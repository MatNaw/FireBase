import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'fire-counter',
  templateUrl: './counter.component.pug',
  styleUrls: ['./counter.component.scss']
})
export class CounterComponent {

  @Input() label: string;
  @Input() value: number;
  @Input() disabled: boolean;

  @Input() minValue: number;
  @Input() maxValue: number;

  @Output() valueChange = new EventEmitter<number>();

  constructor() { }

  onChange() {
    this.valueChange.emit(this.value);
  }

  increment() {
    if (this.isIncrementingAvailable()) {
      ++this.value;
      this.onChange();
    }
  }

  decrement() {
    if (this.isDecrementingAvailable()) {
      --this.value;
      this.onChange();
    }
  }

  isIncrementingAvailable() {
    return !this.disabled && (this.maxValue === undefined || this.value < this.maxValue);
  }

  isDecrementingAvailable() {
    return !this.disabled && (this.minValue === undefined || this.value > this.minValue);
  }

}
