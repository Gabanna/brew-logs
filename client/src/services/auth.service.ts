import { Injectable } from '@angular/core';
import firebase from 'firebase';
import firebaseConfig, {googleProvider} from '../config/firebase';
import { Observer } from 'rxjs/Observer';

@Injectable()
export class AuthService {

    private auth: firebase.auth.Auth;

    public init(): void {
        this.auth = firebase.initializeApp(firebaseConfig).auth();
    }

    public login(): Promise<firebase.User> {
        return this.auth.signInWithRedirect(googleProvider);
    }

    public currentUser(): firebase.User {
        return this.auth.currentUser;
    }

    public onAuthStateChanged(changeHandler : Observer<any> | ((a: firebase.User) => any)): firebase.Unsubscribe {
        return this.auth.onAuthStateChanged(changeHandler);
    }
}