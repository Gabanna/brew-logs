import { NgModule } from "@angular/core";
import { AvatarComponent } from "./avatar/avatar";
import { AvatarMenuComponent } from "./avatar-menu/avatar-menu";
import { IonicModule } from 'ionic-angular';
import { BrewLogComponent } from './brew-log/brew-log';
import { PipesModule } from '../pipes/pipes.module';
@NgModule({
  declarations: [AvatarComponent, AvatarMenuComponent,
    BrewLogComponent],
  imports: [IonicModule, PipesModule],
  exports: [AvatarComponent, AvatarMenuComponent,
    BrewLogComponent],
  entryComponents: [AvatarComponent]
})
export class ComponentsModule {}
