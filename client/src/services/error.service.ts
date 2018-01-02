import { Injectable } from '@angular/core';
import { ToastController } from 'ionic-angular';

@Injectable()
export class ErrorService {

    constructor(
        private toastController: ToastController
    ){}

    public handleHttpError(error: any) {
        /*this.toastController.create({
            message: 'Es ist ein Fehler aufgetreten: ' + error,
            position: 'top',
            cssClass: 'error',
            showCloseButton: true,
            closeButtonText: 'X'
        })
        .present();*/
        
        console.error(error);
    }
}