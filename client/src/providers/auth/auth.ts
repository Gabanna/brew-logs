import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import * as OothClient from "ooth-client";

@Injectable()
export class AuthProvider {
  private oothClient = new OothClient({
    oothUrl: "http://localhost:3000/auth",
    standalone: false
  });

  constructor(public http: HttpClient) {
    this.oothClient.start();
  }

  public getCurrentUser(): Promise<any> {
    return new Promise((resolve, reject) => {
      const subscription = this.oothClient.user().subscribe(user => {
        resolve(user);
      });
    });
  }

  public register(email, password) {
    this.oothClient.authenticate('local', 'register', {
      email: email,
      password: password
    });
  }

  public login(email, password) {
    this.oothClient.authenticate('local', 'login', {
      username: email,
      password: password
    });
  }
}
