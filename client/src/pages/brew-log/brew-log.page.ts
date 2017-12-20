import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { BrewLogService } from '../../services/brewlog.service';
import { ErrorService } from '../../services/error.service';
import { BrewLog } from '../../model/brewLog.model';
import { BrewLogDetailPage } from '../brew-log-detail/brew-log-detail.page';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'page-brew-log',
  templateUrl: 'brew-log.page.html',
})
export class BrewLogPage {

  public brewLogs: Array<BrewLog>;

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public brewLogService: BrewLogService,
    public errorService: ErrorService,
    public authService: AuthService
  ) {  }

  ionViewDidLoad() {

    this.brewLogService.getBrewLogs().then(logs => {
      this.brewLogs = logs;
    })
    .catch(error => this.errorService.handleHttpError(error));
  }

  public openBrewLog(brewLog: BrewLog): void {
    this.navCtrl.setRoot(BrewLogDetailPage, brewLog);
  }

}
