import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { LogListPage } from './log-list';

@NgModule({
  declarations: [
    LogListPage,
  ],
  imports: [
    IonicPageModule.forChild(LogListPage),
  ],
})
export class LogListPageModule {}
