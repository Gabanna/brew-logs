import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ToastController } from 'ionic-angular';
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
    private navCtrl: NavController,
    private navParams: NavParams,
    private brewLogService: BrewLogService,
    private errorService: ErrorService,
    private authService: AuthService,
    private alertController: AlertController,
    private toastController: ToastController
  ) {  }

  ionViewDidLoad() {

    this.brewLogService.getBrewLogs().then(logs => {
      this.brewLogs = logs;
    })
    .catch(error => this.errorService.handleHttpError(error));
  }

  public openBrewLog(brewLog: BrewLog): void {
    this.navCtrl.push(BrewLogDetailPage, brewLog);
  }

  public newBrewLog(): void {
    let prompt = this.alertController.create({
      title: 'neues Log',
      inputs: [
        {
          name: 'name',
          placeholder: 'Name des Projekts'
        }
      ],
      buttons: [
        {
          text: 'Cancle',
          role: 'cancel',
          handler: data => {}
        },
        {
          text: 'Save',
          handler: data => {
            this.handleStart(data);
          }
        }
      ]
    });

    prompt.present();
  }

  private handleStart(data: any): void {
    if(data.name) {
      this.brewLogService.startBrewLog(data.name)
        .then(brewLog => {
          this.brewLogs.push(brewLog);
          this.toastController.create({
            message: 'brew log created',
            cssClass: 'success'
          });
        })
        .catch(error => {
          this.errorService.handleHttpError(error);
        });
    }
  }
}
