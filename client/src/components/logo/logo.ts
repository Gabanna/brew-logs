import { Component, Input } from '@angular/core';

@Component({
  selector: 'logo',
  templateUrl: 'logo.html'
})
export class LogoComponent {

  @Input()
  res: number = 512;

}
