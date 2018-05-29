import { LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { HttpClientModule } from '@angular/common/http';

import { MyApp } from './app.component';
import { AuthProvider } from '../providers/auth';

import * as Raven from 'raven-js';
import { ToastProvider } from '../providers/toastProvider';
import { HomePageModule } from '../pages/home/home.module';
import { LoginPageModule } from '../pages/login/login.module';
import { BrewLogProvider } from '../providers/brew-log-provider';

import { registerLocaleData } from '@angular/common';
import localeDe from '@angular/common/locales/de';
import localeFr from '@angular/common/locales/fr';
import { BrewLogPageModule } from '../pages/brew-log/brew-log.module';
import { LoadingProvider } from '../providers/loading';

registerLocaleData(localeDe, 'de');
registerLocaleData(localeFr, 'fr');

Raven.config('https://50403c15b8ea4b7783eb7ea845741f2c@sentry.io/1191379').install();

export class RavenErrorHandler implements ErrorHandler {
  handleError(err:any) : void {
    Raven.captureException(err.originalError || err);
  }
}

@NgModule({
  declarations: [
    MyApp
  ],
  imports: [
    BrewLogPageModule,
    HomePageModule,
    BrowserModule,
    LoginPageModule,
    HttpClientModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp
  ],
  providers: [
    StatusBar,
    SplashScreen,
    //{provide: ErrorHandler, useClass: RavenErrorHandler},
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AuthProvider,
    BrewLogProvider,
    ToastProvider,
    {provide: LOCALE_ID, useValue: navigator.language },
    LoadingProvider
  ]
})
export class AppModule {}
