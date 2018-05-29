import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import baseUrl from './baseUrl';
import { AuthProvider } from './auth';

@Injectable()
export class BrewLogProvider {
  private readonly url = baseUrl + '/brew-logs'

  constructor(public http: HttpClient, private authProvider: AuthProvider) {}

    public findBrewLogsByUser(username: string): Promise<Array<any>> {
        let authHeaders = this.authProvider.getAuthorizationHeader();
        return <Promise<Array<any>>> this.http.get(this.url + '/user/' + username, {
            headers: authHeaders
        }).toPromise();
    }

    public findProcessForBrewLog(brewLogId: number): Promise<Array<any>> {
      let authHeaders = this.authProvider.getAuthorizationHeader();
      return <Promise<Array<any>>> this.http.get(this.url + '/' + brewLogId + '/process', {
        headers: authHeaders
      }).toPromise();
    }

    public create(brewLog: any): Promise<any> {
        let authHeaders = this.authProvider.getAuthorizationHeader();
        return this.http.post(this.url, brewLog, {
            headers: authHeaders
        }).toPromise();
    }

    public deleteBrewLog(brewLogId: number): Promise<any> {
      let authHeaders = this.authProvider.getAuthorizationHeader();
      return this.http.delete(this.url + '/' + brewLogId, {
        headers: authHeaders
      }).toPromise();
    }
}
