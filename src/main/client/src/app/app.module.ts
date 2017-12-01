import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';

import { OAuthService } from 'angular-oauth2-oidc';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';

import {LoginPage} from '../pages/login/login.page';
import { User } from '../model/user.model';
import { RegisterDialog } from '../dialogs/register/register.dialog';


@NgModule({
  declarations: [
    MyApp,
    HomePage,
    LoginPage,
    RegisterDialog
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    LoginPage,
    RegisterDialog
  ],
  providers: [
    StatusBar,
    HttpClient,
    SplashScreen,
    OAuthService,
    User,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
