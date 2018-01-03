import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import config from '../config/config';
import _ from 'lodash';

@Injectable()
export class TaskService {

    constructor(
        private http: Http
    ) { }

    public getActiveTasks(brewlogId: number): Promise<Array<any>> {
        return new Promise((resolve, reject) => {
            let url = config.api.endpoint + '/brew-logs/' + brewlogId + '/tasks/active';
            this.http.get(url).toPromise()
                .then(data => {
                    resolve(_.assign(new Array<any>(), data.json()));
                })
                .catch(error => reject(error) );
        });
    }

    public getOptions(brewlogId: number): Promise<Array<any>> {
        return new Promise((resolve, reject) => {
            let url = config.api.endpoint + '/brew-logs/' + brewlogId + '/tasks/enabled';
            this.http.get(url).toPromise()
                .then(data => {
                    resolve(_.assign(new Array<any>(), data.json()));
                })
                .catch(error => reject(error));
        });
    }

    public activateOption(option: any): Promise<any> {
        return new Promise((resolve, reject) => {
            let url = config.api.endpoint + '/tasks/' + option.id + '/start';
            this.http.put(url, {}).toPromise()
            .then(data => {
                resolve(_.assign(new Array<any>(), data.json()));
            })
            .catch(error => reject(error));
        });
    }

    public finishTask(task: any): Promise<void> {
        return new Promise((resolve, reject) => {
            let url = config.api.endpoint + '/tasks/' + task.id;
            this.http.post(url, task).toPromise()
            .then(data => {
                resolve();
            })
            .catch(error => reject(error));
        });
    }

    public getHistory(brewLogId: number): Promise<any> {
        return new Promise((resolve, reject) => {
            let url = config.api.endpoint + '/brew-logs/'+ brewLogId +'/tasks/history';
            this.http.get(url).toPromise()
            .then(data => {
                resolve(_.assign(new Array<any>(), data.json()));
            })
            .catch(error => reject(error) );
        });
    }
}