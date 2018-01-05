import { Component, Input } from '@angular/core';

@Component({
  selector: 'brew-history',
  templateUrl: 'brew-history.component.html'
})
export class BrewHistoryComponent  {

  @Input()
  public value: Array<any>;

}
