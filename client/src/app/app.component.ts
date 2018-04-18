import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
<<<<<<< HEAD
import { BrewLogPage } from '../pages/brew-log/brew-log.page';
import { AuthService } from '../services/auth.service';
import { registerLocaleData } from '@angular/common';
import localeDe from '@angular/common/locales/de';
import localeFr from '@angular/common/locales/fr';

=======

import { HomePage } from '../pages/home/home';
>>>>>>> 858e51c... changed to node project
@Component({
  templateUrl: 'app.html'
})
export class MyApp {
<<<<<<< HEAD
  rootPage:any = BrewLogPage;

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen, authService: AuthService) {

    authService.init();

    platform.ready().then(() => {

      registerLocaleData(localeDe);
      registerLocaleData(localeFr);

      authService.onAuthStateChanged(user => {
        if(!user) {
          authService.login();
        }
      });

=======
  rootPage:any = HomePage;

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen) {
    platform.ready().then(() => {
>>>>>>> 858e51c... changed to node project
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleDefault();
      splashScreen.hide();
<<<<<<< HEAD

=======
>>>>>>> 858e51c... changed to node project
    });
  }
}

