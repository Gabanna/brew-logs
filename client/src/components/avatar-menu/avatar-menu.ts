import { Component } from "@angular/core";
import { NavParams, ViewController } from "ionic-angular";

@Component({
  selector: "avatar-menu",
  templateUrl: "avatar-menu.html"
})
export class AvatarMenuComponent {
  email: string;

  constructor(
    private viewCtrl: ViewController,
    navParams: NavParams,
  ) {
    this.email = navParams.get('email');
  }

  public logout(): void {
    this.viewCtrl.dismiss('logout');
  }
}
