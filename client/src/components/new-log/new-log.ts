import { Component } from "@angular/core";
import { ViewController } from "ionic-angular";

@Component({
  selector: "new-log",
  templateUrl: "new-log.html"
})
export class NewLogComponent {

  private brewLog: any = {};

  constructor(private viewCtl: ViewController) {}

  public createNewLog(): void {
    this.viewCtl.dismiss(this.brewLog).catch(console.error);
  }
}
