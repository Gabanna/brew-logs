import { Component, ViewChild } from "@angular/core";
import {
  NavController,
  IonicPage,
  Content,
  ModalController
} from "ionic-angular";
import { AuthProvider } from "../../providers/auth";
import { RavenErrorHandler } from "../../app/app.module";
import { ToastProvider } from "../../providers/toastProvider";
import { BrewLogProvider } from "../../providers/brew-log-provider";
import { RegisterComponent } from "../../components/register/register";
import { NewLogComponent } from "../../components/new-log/new-log";
import { LoadingProvider } from "../../providers/loading";

@IonicPage()
@Component({
  selector: "page-home",
  templateUrl: "home.html"
})
export class HomePage {
  @ViewChild(Content) content: Content;

  private user: any;
  private brewLogs: Array<any> = [];

  constructor(
    public navCtrl: NavController,
    private authProvider: AuthProvider,
    private modalController: ModalController,
    private toastProvider: ToastProvider,
    private brewLogProvider: BrewLogProvider,
    private loadingProvider: LoadingProvider
  ) {}

  ionViewWillEnter() {
    this.setUser();
    this.loadBrewLogs();
  }

  open(brewLog): void {
    this.navCtrl.push("BrewLogPage", { brewLog: brewLog });
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
              .toast("Es ist ein Fehler aufgetreten: " + error)
              .cssClass("error")
              .show();
            console.error(error);
          });
      }
    });

    modal.present();
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
          .show();
        console.error(error);
        this.loadingProvider.hide()
      });
  }

  private setUser() {
    this.user = this.authProvider.getCurrentUser();
    if (!this.user) {
      this.navCtrl.setRoot("LoginPage", {
        from: HomePage
      });
    }
  }
}
