import _ from 'lodash';
import { JsonWebToken } from '../model/jwt.model';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import config from '../config/config';
import {ToastController} from 'ionic-angular';

@Injectable()
export class User {

    public userName: string;
    private password: string;
    public token: JsonWebToken;
    public roles: Array<Role>;

    constructor(private http: Http, private toast: ToastController){}

    public isAuthenticated(): boolean {
        if(!this.token) {
            this.token = _.assign(new JsonWebToken(), JSON.parse(localStorage.getItem('brew.jwt')))
        }

        return this.token && this.token.isValid();
    }

    public login() {
        return new Promise((resolve, reject) =>{
            let url = config.rest.baseUrl + "auth/" + this.userName;
            this.http.post(url, {
                userName: this.userName,
                password: this.password,
                roles: this.roles
            }).toPromise().then(createdUser => {
                _.assign(this, createdUser);
                localStorage.setItem('brew.jwt', JSON.stringify(this.token));
                resolve(this);
            })
            .catch(error => {
                this.toast.create({
                    message: 'unable to login: ' + JSON.stringify(error)
                }).present();
                reject(error);
            });
        });
    }

    public register() {
        return new Promise((resolve, reject) => {
            let url = config.rest.baseUrl + "auth";
            this.http.put(url, {
                userName: this.userName,
                password: this.password
            }).toPromise().then(createdUser => {
                _.assign(this, createdUser);
                localStorage.setItem('brew.jwt', JSON.stringify(this.token));
                resolve(this);
            })
            .catch(error => {
                this.toast.create({
                    message: 'unable to login: ' + JSON.stringify(error)
                }).present();
                reject(error);
            });
        });
    }
}

export class Role {

    public name: string;
    public permissions: Array<Permission>;
}

export class Permission {

}
