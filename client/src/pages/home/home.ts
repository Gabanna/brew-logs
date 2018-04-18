import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AuthProvider } from '../../providers/auth/auth';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  private user: any;

  constructor(public navCtrl: NavController, private authProvider: AuthProvider) {
    //authProvider.login('test@gmail.com', '12qwER');
    authProvider.login('tester@gmail.com', '12qwER');
    this.authProvider.getCurrentUser().then((user) => {
      this.user = user;
    });
  }
}
