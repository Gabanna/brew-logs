import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import md5 from 'md5';

@Injectable()
export class AuthProvider {

  private readonly url = "http://localhost:3000/auth";
  private usercache: any = null;

  constructor(public http: HttpClient) {
  }

  public getCurrentUser(): any {
    return this.usercache || this.readUser();
  }

  private readUser() {
    let user = localStorage.getItem('bl.user');
    if(user) {
      user = JSON.parse(user);
    }
    return user;
  }

  public register(email, password): Promise<any> {
    return this.http.post(this.url + '/register', {
      email: email,
      password: password
    }).toPromise();
  }

  public logout(): void {
    localStorage.removeItem('bl.user');
    this.usercache = null;
  }

  public login(email, password): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http.post(this.url + '/login', {
        email: email,
        password: md5(password)
      }).toPromise().then(user => {
        localStorage.setItem('bl.user', JSON.stringify(user));
        this.usercache = user;
        resolve(user);
      }).catch(reject);
    });
  }
}
