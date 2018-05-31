import {Component} from '@angular/core';
import {IonicPage, NavController, NavParams} from 'ionic-angular';
import {BrewLogProvider} from "../../providers/brew-log-provider";
import {LoadingProvider} from "../../providers/loading";


@IonicPage()
@Component({
  selector: 'page-brew-log',
  templateUrl: 'brew-log.html',
})
export class BrewLogPage {

  brewLog: any = {};
  processes: Array<any>;

  constructor(public brewLogController: BrewLogProvider, public navParams: NavParams, private loadingProvider: LoadingProvider) {

  }

  ionViewWillLoad() {
    this.brewLog = this.navParams.get('brewLog');
    if (this.brewLog) {
      this.loadingProvider.show();
      this.brewLogController.findProcessForBrewLog(this.brewLog.id).then(processes => {
        this.processes = processes;
        this.loadingProvider.hide();
      })
        .catch(error => {
          console.error(error);
          this.loadingProvider.hide();
        });
    }
  }
}
