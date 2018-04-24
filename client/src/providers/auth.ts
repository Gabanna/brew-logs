import { HttpClient, HttpHeaders } from "@angular/common/http";
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
        .post(
          this.url,
          {
            username: username,
            email: email,
            password: password
          },
          { observe: "response" }
        )
        .toPromise()
        .then(response => {
          let authHeader = response.headers.get("Authorization");

          try {
            let user = jwt.verify(
              authHeader.replace("Bearer ", ""),
              config.api.key
            );
            resolve(user);
          } catch (err) {
            reject(err);
          }
        })
        .catch(reject);
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
        .put(
          this.url,
          {
            username: username,
            password: password
          },
          { observe: "response" }
        )
        .toPromise()
        .then(response => {
          let authHeader = response.headers.get("Authorization");
          console.info("response.headers", JSON.stringify(response.headers))

          try {
            let user = jwt.verify(
              authHeader.replace("Bearer ", ""),
              config.api.key
            );
            localStorage.setItem("bl.user", JSON.stringify(user));
            localStorage.setItem("bl.token", authHeader);
            this.usercache = user;
            resolve(user);
          } catch (err) {
            reject(err);
          }
        })
        .catch(reject);
    });
  }
}
