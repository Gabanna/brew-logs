import { Component, Input, OnInit } from '@angular/core';
import { BrewLog } from '../../model/brewLog.model';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'brew-history',
  templateUrl: 'brew-history.component.html'
})
export class BrewHistoryComponent implements OnInit {

  @Input()
  public value: BrewLog;

    public history: Array<any>;

  constructor(
      private taskService: TaskService
  ) {}

  ngOnInit(): void {
    this.taskService.getHistory(this.value.getId()).then(history => this.history = history);
  }
}
