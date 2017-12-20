import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { BrewLog } from '../../model/brewLog.model';
import { TaskService } from '../../services/task.service';
import { ErrorService } from '../../services/error.service';
import { TaskDetailPage } from '../task-detail/task-detail.page';

@Component({
  selector: 'page-brew-log-detail',
  templateUrl: 'brew-log-detail.page.html',
})
export class BrewLogDetailPage {

  private brewLog: BrewLog;
  private tasks: any[];
  private options: any[];

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public taskService: TaskService,
    public errorService: ErrorService
  ) {
    this.brewLog = navParams.data;
  }

  ionViewDidLoad() {
    this.taskService.getActiveTasks(this.brewLog.getId())
      .then(tasks => this.tasks = tasks)
      .catch(this.errorService.handleHttpError);
    this.taskService.getOptions(this.brewLog.getId())
      .then(options => this.options = options)
      .catch(this.errorService.handleHttpError);
  }

  public openTask(task: any) : void {
    this.navCtrl.push(TaskDetailPage, task);
  }
}
