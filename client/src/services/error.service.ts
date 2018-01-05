import { Injectable } from '@angular/core';

@Injectable()
export class ErrorService {

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