import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { BrewLog } from '../model/brewLog.model';
import { AuthService } from './auth.service';
import config from '../config/config';
import _ from 'lodash';

@Injectable()
export class BrewLogService {

    constructor(
        private http: Http,
        private authService: AuthService
    ){}

    public getBrewLogs(): Promise<Array<BrewLog>> {
        return new Promise((resolve, reject) => {
           let unsubscribe = this.authService.onAuthStateChanged(user => {
                unsubscribe();
                if(user) {
                    let url = config.api.endpoint + '/brew-logs/' + encodeURI(this.authService.currentUser().email);
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
                } else {
                    reject('not logged in');
                }
            });
        });
    }  

    public startBrewLog(name: string): Promise<BrewLog> {
        return new Promise((resolve, reject) => {
            let body = {
                name: name,
                subject: this.authService.currentUser().email
            };
            let url = config.api.endpoint + '/brew-logs';
            this.http.post(url, body).toPromise()
                .then(data => {
                    resolve(_.assign(new BrewLog(), data.json()));
                })
                .catch(error => {
                    reject(error);
                });
        });
    }
}