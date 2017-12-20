import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import config from '../config/config';
import { BrewLog } from '../model/brewLog.model';
import _ from 'lodash';
import { AuthService } from './auth.service';

@Injectable()
export class BrewLogService {

    constructor(
        private http: Http,
        private authService: AuthService
    ){}

    public getBrewLogs(): Promise<Array<BrewLog>> {
        return new Promise((resolve, reject) => {
            let url = config.api.endpoint + '/brew-logs/' + this.authService.currentUser();
            this.http.get(url).toPromise()
                .then(data => {
                    let result = new Array<BrewLog>();
                    data.json().forEach(element => {
                        result.push(_.assign(new BrewLog(), element));
                    });
                    resolve(result);
                })
                .catch(error => {
                    reject(error);
                });
        });
    }  
}