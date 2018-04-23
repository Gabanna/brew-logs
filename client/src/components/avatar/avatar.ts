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
  userValue: any;

  @Output()
  private logout = new EventEmitter();

  constructor(private popoverCtrl: PopoverController){}

  @Input()
  set user(user: any) {
    this.userValue = user;
    if(user) {
      this.gravatarUrl = this.gravatarUrlTemplate.replace('<hash>', md5(user.email));
    }
  }

  public showMenu(event): void {
    var popover = this.popoverCtrl.create(AvatarMenuComponent, {username: this.userValue.username});

    popover.onDidDismiss((data, role) => {
      if(data) {
        this.logout.emit(data);
      }
    });

    popover.present({
      ev: event
    })
  }
}
