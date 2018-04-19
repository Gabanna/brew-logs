import { Injectable } from "@angular/core";
import { ToastController, ToastOptions } from "ionic-angular";

@Injectable()
export class ToastProvider {
  constructor(private toastCtrl: ToastController) {}

  public toast(message: string): ToastBuilder {
    return new ToastBuilder(this.toastCtrl, message);
  }
}

class ToastBuilder {
  private toastCtrl: ToastController;
  private toastOpts: ToastOptions;

  constructor(toastCtrl: ToastController, message: string) {
    this.toastCtrl = toastCtrl;
    this.toastOpts = {
      message: message,
      position: 'top',
      closeButtonText: 'X'
    };
  }

  message(message: string): ToastBuilder {
    this.toastOpts.message = message;
    return this;
  }

  cssClass(cssClass: string): ToastBuilder {
    this.toastOpts.cssClass = cssClass;
    return this;
  }

  duration(duration: number): ToastBuilder {
    this.toastOpts.duration = duration;
    return this;
  }

  showCloseButton(showCloseButton: boolean): ToastBuilder {
    this.toastOpts.showCloseButton = showCloseButton;
    return this;
  }

  closeButtonText(closeButtonText: string): ToastBuilder {
    this.toastOpts.closeButtonText = closeButtonText;
    return this;
  }

  dismissOnPageChange(dismissOnPageChange: boolean): ToastBuilder {
    this.toastOpts.dismissOnPageChange = dismissOnPageChange;
    return this;
  }

  position(position: string): ToastBuilder {
    this.toastOpts.position = position;
    return this;
  }

  show(): Promise<any> {
    return this.toastCtrl.create(this.toastOpts).present();
  }
}
