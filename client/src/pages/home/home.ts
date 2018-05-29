import { Component, ViewChild } from "@angular/core";
import {
  NavController,
  IonicPage,
  Content,
  ModalController,
  AlertController
} from "ionic-angular";
import { AuthProvider } from "../../providers/auth";
import { ToastProvider } from "../../providers/toastProvider";
import { BrewLogProvider } from "../../providers/brew-log-provider";
import { NewLogComponent } from "../../components/new-log/new-log";
import { LoadingProvider } from "../../providers/loading";
import {I18nPipe} from "../../pipes/i18n/i18n";

@IonicPage()
@Component({
  selector: "page-home",
  templateUrl: "home.html"
})
export class HomePage {
  @ViewChild(Content) content: Content;

  user: any;
  brewLogs: Array<any>;

  private i18n: I18nPipe = new I18nPipe()

  constructor(
    public navCtrl: NavController,
    private authProvider: AuthProvider,
    private modalController: ModalController,
    private toastProvider: ToastProvider,
    private brewLogProvider: BrewLogProvider,
    private loadingProvider: LoadingProvider,
    private alertCtrl: AlertController
  ) {
  }

  ionViewWillEnter() {
    this.setUser();
    this.loadBrewLogs();
  }

  open(brewLog): void {
    this.navCtrl.push("BrewLogPage", { brewLog: brewLog }).catch(console.error);
  }

  logout(): void {
    this.authProvider.logout();
    this.setUser();
  }

  newLog(): void {
    let modal = this.modalController.create(NewLogComponent);
    modal.onWillDismiss(brewLog => {
      if (brewLog) {
        this.brewLogProvider
          .create(brewLog)
          .then(() => {
            this.loadBrewLogs();
          })
          .catch(error => {
            this.toastProvider
              .toast(this.i18n.transform("errorOccurred")+ ": " + error)
              .cssClass("error")
              .show().catch(console.error);
            console.error(error);
          });
      }
    });

    modal.present().catch(console.error);
  }

  remove(toDelete) {
    let alert = this.alertCtrl.create({
      message: this.i18n.transform('shouldDelete') + '?',
      buttons: [
        {
          text: this.i18n.transform("cancle"),
          role: 'cancel',
          handler: () => {
            console.log('Cancel clicked');
          }
        },
        {
          text: this.i18n.transform("yes"),
          handler: () => {
            this.brewLogProvider.deleteBrewLog(toDelete.id).then(() => {
              this.loadBrewLogs();
              this.toastProvider
                .toast(this.i18n.transform("deletionSuccessful"))
                .cssClass("success")
                .duration(5000)
                .show().catch(console.error);
            })
              .catch(console.error);
          }
        }
      ]
    });
    alert.present();
  }

  private loadBrewLogs() {
    this.loadingProvider.show();
    this.brewLogProvider
      .findBrewLogsByUser(this.user.username)
      .then(brewLogs => {
        this.brewLogs = brewLogs;
        this.loadingProvider.hide()
      })
      .catch(error => {
        this.toastProvider
          .toast("Es ist ein Fehler aufgetreten: " + error)
          .cssClass("error")
          .show().catch(console.error);
        console.error(error);
        this.loadingProvider.hide()
      });
  }

  private setUser() {
    this.user = this.authProvider.getCurrentUser();
    if (!this.user) {
      this.navCtrl.setRoot("LoginPage", {
        from: HomePage
      }).catch(console.error);
    }
  }
}
