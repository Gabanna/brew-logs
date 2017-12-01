import { Component, Injectable, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import config from '../config/config';
import { User } from '../model/user.model';

@Injectable()
export class AuthService implements OnInit {

    constructor(
        private http: Http,
        private subject: User
    ){}

    ngOnInit() {
        this.subject.isAuthenticated();
    }
}