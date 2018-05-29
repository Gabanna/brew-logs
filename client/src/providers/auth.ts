import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from "@angular/core";
import config from "../config/config";
import baseUrl from './baseUrl';
import jwt from "jsonwebtoken";

@Injectable()
export class AuthProvider {

  private readonly url = baseUrl + '/users';

  private usercache: any = null;

  constructor(public http: HttpClient) {}

  public getCurrentUser(): any {
    return this.usercache || this.readUser();
  }

  private readUser() {
    let user = localStorage.getItem("bl.user");
    if (user) {
      user = JSON.parse(user);
    }
    return user;
  }

  public getAuthorizationHeader(headers?: HttpHeaders): HttpHeaders {
    let result = headers || new HttpHeaders();
    return result.set('Authorization', localStorage.getItem("bl.token"));
  }

  public register(username, email, password): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http
        .post<{token: string}>(
          this.url,
          {
            username: username,
            email: email,
            password: password
          }
        )
        .subscribe(token => {
          try {
            let user = this.processToken(token);
            resolve(user);
          } catch (err) {
            reject(err);
          }
        }, error => reject(error))
    });
  }

  public logout(): void {
    localStorage.removeItem("bl.user");
    localStorage.removeItem("bl.token");
    this.usercache = null;
  }

  public login(username, password): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http
        .put<{token: string}>(
          this.url,
          {
            username: username,
            password: password
          }
        )
        .subscribe(authHeader => {
          try {
            let user = this.processToken(authHeader);
            resolve(user);
          } catch (err) {
            reject(err);
          }
        }, error => reject(error))
    });
  }

  private processToken(token: {token: string}): any {
    let trimmed = token.token.replace("Bearer ", "");
    let apiKey = config.api.key;
    let user = jwt.verify(
      trimmed,
      apiKey
    );
    localStorage.setItem("bl.user", JSON.stringify(user));
    localStorage.setItem("bl.token", token.token);
    this.usercache = user;
    return user;
  }
}
