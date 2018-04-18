import { Component } from '@angular/core';
import { NavController, ToastController } from 'ionic-angular';
import { AuthProvider } from '../../providers/auth/auth';
import { RavenErrorHandler } from '../../app/app.module';
import { ToastProvider } from '../../providers/toastProvider';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  private user: any;

  constructor(public navCtrl: NavController, private authProvider: AuthProvider, private toastProvider: ToastProvider) {
    authProvider.logout();
    authProvider.login('tester@gmail.com', '12qwER').then(user => this.user = user).catch(error => this.showError(error));
  }

  private showError(error) {
    this.toastProvider.toast(error).cssClass('error').show();
  }
}
