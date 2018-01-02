import { Component, Input } from '@angular/core';

@Component({
  selector: 'form-field',
  templateUrl: 'form-field.component.html'
})
export class FormFieldComponent {

  @Input()
  value: any;

  constructor() {
  }

}
