import { Component, Input, Output, EventEmitter } from '@angular/core';
import md5 from 'md5';
import { PopoverController } from 'ionic-angular';
import { AvatarMenuComponent } from '../avatar-menu/avatar-menu';

@Component({
  selector: 'avatar',
  templateUrl: 'avatar.html'
})
export class AvatarComponent {

  private readonly gravatarUrlTemplate: string = 'https://www.gravatar.com/avatar/<hash>?s=32&d=robohash';
  gravatarUrl: string;
  emailValue: string;

  @Output()
  private logout = new EventEmitter();

  constructor(private popoverCtrl: PopoverController){}

  @Input()
  set email(email: string) {
    this.emailValue = email;
    if(email) {
      this.gravatarUrl = this.gravatarUrlTemplate.replace('<hash>', md5(email));
    }
  }

  public showMenu(event): void {
    var popover = this.popoverCtrl.create(AvatarMenuComponent, {email: this.emailValue});

    popover.onDidDismiss((data, role) => {
      this.logout.emit(data);
    });

    popover.present({
      ev: event
    })
  }
}
