import { Component, ViewChild } from '@angular/core';
import { NavController, ModalController } from 'ionic-angular';
import {HomePage} from '../home/home';
import { User } from '../../model/user.model';
import { RegisterDialog } from '../../dialogs/register/register.dialog';
import _ from 'lodash';

@Component({
  selector: 'page-login',
  templateUrl: 'login.page.html'
})
export class LoginPage {
  @ViewChild('email') email: any;
  private password: string;
  private error: string;

  constructor(
    private navCtrl: NavController,
    private modal: ModalController,
    private user: User) {
  }

  ionViewDidLoad(): void {
    setTimeout(() => {
      this.email.setFocus();
    }, 500);
  }

  login(): void {
    this.user.login();
  }

  onRegister() {
    var dialog = this.modal.create(RegisterDialog, {user: this.user});
    dialog.onDidDismiss(data => {
      if(data) {
        this.user = _.assign(this.user, data);
      }
    });
    dialog.present();
  }
}