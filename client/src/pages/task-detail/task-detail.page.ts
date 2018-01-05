import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { TaskService } from '../../services/task.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'page-task-detail',
  templateUrl: 'task-detail.page.html',
})
export class TaskDetailPage {

  private task: any;
  private formGroup: FormGroup;

  constructor(
    private navCtrl: NavController,
    private navParams: NavParams,
    private taskService: TaskService,
    private formBuilder: FormBuilder
  ) {
    this.task = navParams.data;

    let form = {};
    this.task.form.forEach(element => {
        let validator = element.required ? Validators.required : null;
        form[element.key_] = [
          element.value ? element.value : '', validator
        ];
    });

    this.formGroup = this.formBuilder.group(form);
  }

  public onClick(): void {
    if(this.formGroup.valid) {
      this.taskService.finishTask(this.task).then(() => {
        this.navCtrl.pop();
      });
    }
  }
}
