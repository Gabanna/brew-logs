import { Component } from '@angular/core';
import { NavParams,ViewController } from 'ionic-angular';
import { User } from '../../model/user.model';

@Component({
  templateUrl: 'register.dialog.html'
})
export class RegisterDialog {

  private user: User;
  private userName: String;
  private password: String;
  private passwordRepeat: String;

  constructor(params: NavParams, public viewController: ViewController) {
    this.user = params.get('user');
  }

  public submit() {
    this.user.register();
  }

  public closeModal() {
    this.viewController.dismiss();
  }

}