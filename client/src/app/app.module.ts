import { I18nPipe } from './../pipes/i18n.pipe';
import { UserComponent } from './../components/user/user.component';
import { BrewHistoryComponent } from './../components/brew-history/brew-history.component';
import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { BrewLogService } from '../services/brewlog.service';
import { HttpModule } from '@angular/http';
import { ErrorService } from '../services/error.service';
import { TaskService } from '../services/task.service';
import { AuthService } from '../services/auth.service';

import { MyApp } from './app.component';
import { BrewLogPage } from '../pages/brew-log/brew-log.page';
import { BrewLogComponent } from '../components/brew-log/brew-log.component';
import { BrewLogDetailPage } from '../pages/brew-log-detail/brew-log-detail.page';
import { TaskDetailPage } from '../pages/task-detail/task-detail.page';
import { FormFieldComponent } from '../components/form-field/form-field.component';

@NgModule({
  declarations: [
    MyApp,
    BrewLogPage,
    BrewLogDetailPage,
    TaskDetailPage,
    BrewLogComponent,
    FormFieldComponent,
    BrewHistoryComponent,
    UserComponent,
    I18nPipe
  ],
  imports: [
    BrowserModule,
    HttpModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
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
    FormFieldComponent,
    BrewHistoryComponent,
    UserComponent,
    AuthService,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ],

})
export class AppModule {}
