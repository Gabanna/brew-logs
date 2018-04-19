import { NgModule } from "@angular/core";
import { AvatarComponent } from "./avatar/avatar";
import { AvatarMenuComponent } from "./avatar-menu/avatar-menu";
import { IonicModule } from 'ionic-angular';
import { I18nPipe } from '../pipes/i18n/i18n';
import { BrewLogComponent } from './brew-log/brew-log';
@NgModule({
  declarations: [AvatarComponent, AvatarMenuComponent,
    BrewLogComponent],
  imports: [IonicModule],
  exports: [AvatarComponent, AvatarMenuComponent,
    BrewLogComponent],
  entryComponents: [AvatarComponent]
})
export class ComponentsModule {}
