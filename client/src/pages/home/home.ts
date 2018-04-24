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
import { RegisterComponent } from '../../components/register/register';

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
    private brewLogProvider: BrewLogProvider
  ) {
    this.setUser();
    this.brewLogProvider
      .findBrewLogsByUser(this.user.username)
      .then(brewLogs => (this.brewLogs = brewLogs))
      .catch(error => {
        this.toastProvider.toast('Es ist ein Fehler aufgetreten: ' + error).cssClass('error').show();
        console.error(error);
      });
  }

  open(brewLog): void {
    this.navCtrl.push('BrewLogPage', {brewLog: brewLog});
  }

  logout(): void {
    this.authProvider.logout();
    this.setUser();
  }

  newLog(): void {
    this.modalController.create(RegisterComponent, this.user);
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
