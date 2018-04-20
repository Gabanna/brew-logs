import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { LoginPage } from './login';
import { PipesModule } from '../../pipes/pipes.module';
import { ComponentsModule } from '../../components/components.module';

@NgModule({
    declarations: [
      LoginPage
    ],
    imports: [
      ComponentsModule,
      PipesModule,
      IonicPageModule.forChild(LoginPage),
    ],
    entryComponents: [
      LoginPage
    ]
  })
  export class LoginPageModule {}