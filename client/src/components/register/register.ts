import { Component } from "@angular/core";
import { NavParams, ViewController } from 'ionic-angular';
import { AuthProvider } from "../../providers/auth";
import { ToastProvider } from "../../providers/toastProvider";
import md5 from 'md5';

@Component({
  selector: "register",
  templateUrl: "register.html"
})
export class RegisterComponent {
  private register: any;

  constructor(
    private navParams: NavParams,
    private viewCtl: ViewController,
    private authProvider: AuthProvider,
    private toastProvider: ToastProvider
  ) {
    this.register = this.navParams.data ? Object.assign({}, this.navParams.data) : {};
  }

  doRegister(): void {
    this.authProvider
      .register(this.register.username, this.register.email, md5(this.register.password))
      .then(data => {
        this.viewCtl.dismiss(data).catch(console.error)
      })
      .catch(error => {
        this.toastProvider
          .toast(error)
          .cssClass("error")
          .showCloseButton(true)
          .show().catch(console.error);
      });
  }
}
