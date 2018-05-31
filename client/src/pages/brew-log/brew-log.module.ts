import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { BrewLogPage } from './brew-log';
import {ComponentsModule} from "../../components/components.module";

@NgModule({
  declarations: [
    BrewLogPage,
  ],
  imports: [
    ComponentsModule,
    IonicPageModule.forChild(BrewLogPage),
  ],
})
export class BrewLogPageModule {}
