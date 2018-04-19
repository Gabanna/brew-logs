import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class BrewLogProvider {
  private readonly url = "http://localhost:3000/brew-logs";

  constructor(public http: HttpClient) {}

    public findBrewLogsByUser(email: string): Promise<Array<any>> {
        return <Promise<Array<any>>> this.http.get(this.url + '/user/' + email).toPromise();
    }
}
