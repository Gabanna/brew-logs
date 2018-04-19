import { Component, Input } from '@angular/core';


@Component({
  selector: 'brew-log',
  templateUrl: 'brew-log.html'
})
export class BrewLogComponent {

  @Input()
  value: any;

}
