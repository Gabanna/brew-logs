import {Component, Input, ViewChild} from '@angular/core';
import { ItemSliding } from 'ionic-angular';

/**
 * Generated class for the FormFieldComponent component.
 *
 * See https://angular.io/api/core/Component for more info on Angular
 * Components.
 */
@Component({
  selector: 'form-field',
  templateUrl: 'form-field.html'
})
export class FormFieldComponent {

  @Input()
  field: any;

  @ViewChild(ItemSliding)
  private sliders: Array<ItemSliding>;

  addItem(event) {
    if(!this.field.value) {
      this.field.value = [];
    }
    this.field.value.push({editable: true});
    event.cancelBubble = true;
    event.stopPropagation();
    return false;
  }

  removeItem(item) {
    let index = this.field.value.indexOf(item);
    if(index > -1) {
      this.field.value.splice(index, 1);
    }
  }

  onSave(event, listItem) {
    listItem.editable = false;
    let t = event.target;
    debugger
  }
}
