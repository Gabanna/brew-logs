import { NgModule } from "@angular/core";
import { AvatarComponent } from "./avatar/avatar";
import { AvatarMenuComponent } from "./avatar-menu/avatar-menu";
import { IonicModule } from 'ionic-angular';
import { BrewLogComponent } from './brew-log/brew-log';
import { PipesModule } from '../pipes/pipes.module';
import { LogoComponent } from './logo/logo';
import { RegisterComponent } from './register/register';

@NgModule({
  declarations: [AvatarComponent, AvatarMenuComponent,
    BrewLogComponent,
    LogoComponent,
    RegisterComponent],
  imports: [IonicModule, PipesModule],
  exports: [
    AvatarComponent,
    AvatarMenuComponent,
    BrewLogComponent,
    LogoComponent,
    RegisterComponent],
  entryComponents: [AvatarComponent, RegisterComponent]
})
export class ComponentsModule {}
