import { Injectable } from '@angular/core';
import * as $ from 'jquery';

@Injectable()
export class LoadingProvider {

  private readonly className = 'loading-indicator';

  public show(): void {
    let loadingIndicator = $('.' + this.className);
    if(loadingIndicator.length == 0) {
      let wrapper = $('<div class="'+this.className+'"><span class="runner"></span></div>');
      $('ion-header').append(wrapper);
    }
  }

  public hide(): void {
    let loadingIndicator = $('.' + this.className);
    if(loadingIndicator.length > 0) {
      loadingIndicator.remove();
    }
  }
}
