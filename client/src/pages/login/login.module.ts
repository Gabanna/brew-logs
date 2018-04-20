import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { LoginPage } from './login';
import { PipesModule } from '../../pipes/pipes.module';

@NgModule({
    declarations: [
      LoginPage
    ],
    imports: [
      PipesModule,
      IonicPageModule.forChild(LoginPage),
    ],
    entryComponents: [
      LoginPage
    ]
  })
  export class LoginPageModule {}