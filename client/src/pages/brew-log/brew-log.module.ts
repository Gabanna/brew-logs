import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { BrewLogPage } from './brew-log';

@NgModule({
  declarations: [
    BrewLogPage,
  ],
  imports: [
    IonicPageModule.forChild(BrewLogPage),
  ],
})
export class BrewLogPageModule {}
