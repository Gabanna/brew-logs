import { Component, Input } from '@angular/core';

@Component({
  selector: 'brew-history-entry',
  templateUrl: 'brew-history-entry.component.html'
})
export class BrewHistoryEntryComponent  {

  @Input()
  public value: any;
}
