import { Component } from '@angular/core';
import { NavController, NavParams, LoadingController } from 'ionic-angular';
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
  private history: Array<any>;

  constructor(
    private navCtrl: NavController,
    private navParams: NavParams,
    private taskService: TaskService,
    private errorService: ErrorService,
    private loading: LoadingController
  ) {
    this.brewLog = this.navParams.data;
  }

  ionViewWillEnter() {
    let l = this.loading.create();
    l.present();

    this.taskService.getHistory(this.brewLog.getId()).then(history => this.history = history);

    this.taskService.getActiveTasks(this.brewLog.getId())
      .then(tasks => {
        this.tasks = tasks;
        if(l) {
          l.dismiss();
          l = null;
        }
      })
      .catch(this.errorService.handleHttpError);

    this.taskService.getOptions(this.brewLog.getId())
      .then(options => {
        this.options = options;
        if(l) {
          l.dismiss();
          l = null;
        }
      })
      .catch(this.errorService.handleHttpError);

  }

  public openTask(task: any) : void {
    this.navCtrl.push(TaskDetailPage, task);
  }

  public activateOption(option: any): void {
    this.taskService.activateOption(option).then(task => {
      this.tasks.push(task);
      this.openTask(task);
    });
  }
}
