import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { SubjectService } from './subject.service';
import config from '../config/config';

@Injectable()
export class TaskService {

    constructor(
        private http: Http,
        private subject: SubjectService
    ) { }

    public getActiveTasks(): Array<any> {

    }  
}