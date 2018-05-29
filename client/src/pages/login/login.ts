import {Component} from '@angular/core';
import {
  NavController,
  IonicPage,
  NavParams,
  ModalController
} from 'ionic-angular';
import {AuthProvider} from '../../providers/auth';
import {ToastProvider} from '../../providers/toastProvider';
import {I18nPipe} from '../../pipes/i18n/i18n';
import {RegisterComponent} from '../../components/register/register';
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
    private modalController: ModalController
  ) {
    this.i18n = new I18nPipe();
  }

  public doLogin() {
    this.authProvider
      .login(this.login.username, md5(this.login.password))
      .then(data => {
          this.onLogin(data);
      })
      .catch(error => {
        if (error.status == 401) {
          this.toastProvider
            .toast(this.i18n.transform('loginFailed'))
            .position('top')
            .cssClass('error')
            .duration(5000)
            .show().catch(console.error);
          this.login.password = null;
        } else {
          this.toastProvider
            .toast(error)
            .cssClass('error')
            .showCloseButton(true)
            .show().catch(console.error);
          console.error(error)
        }
      });
  }

  public doRegister() {
    let registerModal = this.modalController.create(RegisterComponent, this.login);
    registerModal.onWillDismiss(data => {
      if (data) {
        this.onLogin(data);
      }
    });
    registerModal.present().catch(console.error);
  }

  private onLogin(user: {username: string}) {
    let target = this.navParams.get('from');
    this.navCtrl.setRoot(target ? target : 'HomePage').catch(console.error);
    this.toastProvider
      .toast(this.i18n.transform('welcome') + ' ' + user.username)
      .cssClass('success')
      .duration(5000)
      .show().catch(console.error);
  }
}
