import { Component } from '@angular/core';
import {
  NavController,
  ToastController,
  IonicPage,
  NavParams
} from 'ionic-angular';
import { AuthProvider } from '../../providers/auth/auth';
import { RavenErrorHandler } from '../../app/app.module';
import { ToastProvider } from '../../providers/toastProvider';
import { I18nPipe } from '../../pipes/i18n/i18n';

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {

  private login: { username?: string; password?: string } = {};
  private user: any;
  private i18n: I18nPipe;

  constructor(
    public navCtrl: NavController,
    private navParams: NavParams,
    private authProvider: AuthProvider,
    private toastProvider: ToastProvider,
  ) {
    this.user = authProvider.getCurrentUser();
    this.i18n = new I18nPipe();
  }

  public doLogin() {
    this.authProvider
      .login(this.login.username, this.login.password)
      .then(data => {
        
        let target = this.navParams.get('from');
        this.navCtrl.setRoot(target ? target : 'HomePage');
        this.toastProvider
          .toast(this.i18n.transform('welcome') + ' ' + data.email)
          .cssClass('success')
          .duration(5000)
          .show();
      })
      .catch(error => {
        this.toastProvider
          .toast(error)
          .cssClass('error')
          .showCloseButton(true)
          .show();
      });
  }

  public doRegister() {
    this.authProvider
      .register(this.login.username, this.login.password)
      .then(data => {
        console.info(JSON.stringify(data));
        this.doLogin();
      })
      .catch(error => {
        this.toastProvider
          .toast(error)
          .cssClass('error')
          .showCloseButton(true)
          .show();
      });
  }
}
