<<<<<<< HEAD
import { I18nPipe } from './../pipes/i18n.pipe';
import { UserComponent } from './../components/user/user.component';
import { BrewHistoryComponent } from './../components/brew-history/brew-history.component';
=======
>>>>>>> 858e51c... changed to node project
import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
<<<<<<< HEAD
import { BrewLogService } from '../services/brewlog.service';
import { HttpModule } from '@angular/http';
import { ErrorService } from '../services/error.service';
import { TaskService } from '../services/task.service';
import { AuthService } from '../services/auth.service';

import { LOCALE_ID } from '@angular/core';
import { MyApp } from './app.component';
import { BrewLogPage } from '../pages/brew-log/brew-log.page';
import { BrewLogComponent } from '../components/brew-log/brew-log.component';
import { BrewLogDetailPage } from '../pages/brew-log-detail/brew-log-detail.page';
import { TaskDetailPage } from '../pages/task-detail/task-detail.page';
import { BrewHistoryEntryComponent } from '../components/brew-history-entry/brew-history-entry.component';
=======
import { HttpModule } from '@angular/http'
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { AuthProvider } from '../providers/auth/auth';
>>>>>>> 858e51c... changed to node project

@NgModule({
  declarations: [
    MyApp,
<<<<<<< HEAD
    BrewLogPage,
    BrewLogDetailPage,
    TaskDetailPage,
    BrewLogComponent,
    BrewHistoryComponent,
    BrewHistoryEntryComponent,
    UserComponent,
    I18nPipe
=======
    HomePage
>>>>>>> 858e51c... changed to node project
  ],
  imports: [
    BrowserModule,
    HttpModule,
<<<<<<< HEAD
=======
    HttpClientModule,
>>>>>>> 858e51c... changed to node project
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
<<<<<<< HEAD
    BrewLogPage,
    BrewLogDetailPage,
    TaskDetailPage
  ],
  providers: [
    StatusBar,
    BrewLogService,
    ErrorService,
    TaskService,
    SplashScreen,
    BrewLogComponent,
    BrewHistoryComponent,
    BrewHistoryEntryComponent,
    UserComponent,
    AuthService,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    {provide: LOCALE_ID, useValue: navigator.language }
  ],

=======
    HomePage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AuthProvider
  ]
>>>>>>> 858e51c... changed to node project
})
export class AppModule {}
