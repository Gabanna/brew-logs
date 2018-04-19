import { Pipe, PipeTransform } from '@angular/core';
import data from './i18n.data';

@Pipe({
  name: 'i18n',
})
export class I18nPipe implements PipeTransform {
  
  transform(value: string, ...args) {
    let language = navigator.language;
    let targetLanguage = data[language]
    let translation = targetLanguage && targetLanguage[value] ? targetLanguage[value] : '??' + value + '??';
    return translation;
  }
}
