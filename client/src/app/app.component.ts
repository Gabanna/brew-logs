import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { BrewLogPage } from '../pages/brew-log/brew-log.page';
import { AuthService } from '../services/auth.service';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  rootPage:any = BrewLogPage;

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen, authService: AuthService) {

    authService.init();

    platform.ready().then(() => {

      authService.onAuthStateChanged(user => {
        if(!user) {
          authService.login();
        }
      });

      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleDefault();
      splashScreen.hide();

    });
  }
}

