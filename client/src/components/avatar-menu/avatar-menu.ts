import { Component } from "@angular/core";
import { NavParams, ViewController } from "ionic-angular";

@Component({
  selector: "avatar-menu",
  templateUrl: "avatar-menu.html"
})
export class AvatarMenuComponent {
  username: string;

  constructor(
    private viewCtrl: ViewController,
    navParams: NavParams,
  ) {
    this.username = navParams.get('username');
  }

  public logout(): void {
    this.viewCtrl.dismiss('logout');
  }
}
