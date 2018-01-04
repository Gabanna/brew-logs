import { Dictionary } from './../config/i18n';
import { Pipe, PipeTransform } from '@angular/core';
import i18n from '../config/i18n';

@Pipe({name: 'i18n'})
export class I18nPipe implements PipeTransform {
  
    transform(value: string, lang?: string): any {
        let result = '??' + value + '??';
        let userLang = lang ? lang : navigator.language;

        let dict: Dictionary = i18n.get(userLang);
        
        if(!dict) {
            dict = i18n.get('en');
        }

        return dict[value] ? dict[value] : result ;
    }

}