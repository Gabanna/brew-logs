import { I18nPipe } from './../../pipes/i18n.pipe';
import { Component } from '@angular/core';
import { NavController, AlertController, ToastController, LoadingController } from 'ionic-angular';
import { BrewLogService } from '../../services/brewlog.service';
import { ErrorService } from '../../services/error.service';
import { BrewLog } from '../../model/brewLog.model';
import { BrewLogDetailPage } from '../brew-log-detail/brew-log-detail.page';

@Component({
  selector: 'page-brew-log',
  templateUrl: 'brew-log.page.html',
})
export class BrewLogPage {
  
  public brewLogs: Array<BrewLog>;
  private i18n: I18nPipe = new I18nPipe();

  constructor(
    private navCtrl: NavController,
    private brewLogService: BrewLogService,
    private errorService: ErrorService,
    private alertController: AlertController,
    private toastController: ToastController,
    private loading: LoadingController
  ) {  }

  ionViewWillEnter() {
    let l = this.loading.create();
    l.present();

    this.brewLogService.getBrewLogs().then(logs => {
      this.brewLogs = logs;
      l.dismiss();
    })
    .catch(error => {
      l.dismiss();
      this.errorService.handleHttpError(error);
    });
  }

  public openBrewLog(brewLog: BrewLog): void {
    this.navCtrl.push(BrewLogDetailPage, brewLog);
  }

  public newBrewLog(): void {
    let prompt = this.alertController.create({
      title: this.i18n.transform('newLog'),
      inputs: [
        {
          name: 'name',
          placeholder: this.i18n.transform('nameOfProject')
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
