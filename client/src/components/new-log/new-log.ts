import { Component } from "@angular/core";
import { NavParams, ViewController, ToastController } from "ionic-angular";
import { AuthProvider } from "../../providers/auth";
import { ToastProvider } from "../../providers/toastProvider";
import md5 from "md5";
import { BrewLogProvider } from '../../providers/brew-log-provider';

@Component({
  selector: "new-log",
  templateUrl: "new-log.html"
})
export class NewLogComponent {

  private brewLog: any = {};

  constructor(private navParams: NavParams, private viewCtl: ViewController) {}

  public createNewLog(): void {
    this.viewCtl.dismiss(this.brewLog);
  }

  public cancle(): void {
    this.viewCtl.dismiss();
  }
}
