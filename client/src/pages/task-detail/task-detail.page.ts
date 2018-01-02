import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

@Component({
  selector: 'page-task-detail',
  templateUrl: 'task-detail.page.html',
})
export class TaskDetailPage {

  private task: any;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.task = navParams.data;
  }

  ionViewDidLoad() {
  }

  public onClick(): void {
      console.info(this.task.form);
  }

}
