import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'page-task-detail',
  templateUrl: 'task-detail.page.html',
})
export class TaskDetailPage {

  private task: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, private taskService: TaskService) {
    this.task = navParams.data;
  }

  public onClick(): void {
      this.taskService.finishTask(this.task).then(() => { 
        this.navCtrl.pop();
      });
  }

}
