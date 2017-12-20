import { Component, Input } from '@angular/core';
import { BrewLog } from '../../model/brewLog.model';

@Component({
  selector: 'brew-log',
  templateUrl: 'brew-log.component.html'
})
export class BrewLogComponent {

  @Input()
  public value: BrewLog;

  constructor() {}

}
