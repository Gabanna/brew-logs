import {Component} from '@angular/core';
import {IonicPage, NavController, NavParams} from 'ionic-angular';
import {BrewLogProvider} from "../../providers/brew-log-provider";

@IonicPage()
@Component({
  selector: 'page-brew-log',
  templateUrl: 'brew-log.html',
})
export class BrewLogPage {

  brewLog: any = {};
  processes: Array<any>;

  constructor(public brewLogController: BrewLogProvider, public navParams: NavParams) {
  }

  ionViewWillLoad() {
    this.brewLog = this.navParams.get('brewLog');
    if (this.brewLog) {
      this.brewLogController.findProcessForBrewLog(this.brewLog.id).then(processes => {
        this.processes = processes;
      })
        .catch(console.error);
    }
  }

}
