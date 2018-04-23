import { Component } from '@angular/core';
import {
  NavController,
  IonicPage,
  NavParams,
  ModalController
} from 'ionic-angular';
import { AuthProvider } from '../../providers/auth/auth';
import { RavenErrorHandler } from '../../app/app.module';
import { ToastProvider } from '../../providers/toastProvider';
import { I18nPipe } from '../../pipes/i18n/i18n';
import { RegisterComponent } from '../../components/register/register';
import md5 from 'md5';

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {

  private login: { username?: string; password?: string } = {};
  private i18n: I18nPipe;

  constructor(
    private navCtrl: NavController,
    private navParams: NavParams,
    private authProvider: AuthProvider,
    private toastProvider: ToastProvider,
    private modalContoller: ModalController
  ) {
    this.i18n = new I18nPipe();
  }

  public doLogin() {
    this.authProvider
      .login(this.login.username, md5(this.login.password))
      .then(data => {
        
        let target = this.navParams.get('from');
        this.navCtrl.setRoot(target ? target : 'HomePage');
        this.toastProvider
          .toast(this.i18n.transform('welcome') + ' ' + data.username)
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
        console.error(error)
      });
  }

  public doRegister() {
    let registerModal = this.modalContoller.create(RegisterComponent, this.login);
    registerModal.onWillDismiss(data => {
      if(data) {
        this.login = data.user;
        this.login.password = data.password;
        this.doLogin();
      }
    });
    registerModal.present();
  }
}
