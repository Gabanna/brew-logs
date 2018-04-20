import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-brew-log',
  templateUrl: 'brew-log.html',
})
export class BrewLogPage {

  brewLog: any = {};

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewWillLoad() {
      this.brewLog = this.navParams.get('brewLog');
  }

}
