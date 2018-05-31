import { NgModule } from "@angular/core";
import { AvatarComponent } from "./avatar/avatar";
import { AvatarMenuComponent } from "./avatar-menu/avatar-menu";
import { IonicModule } from "ionic-angular";
import { BrewLogComponent } from "./brew-log/brew-log";
import { PipesModule } from "../pipes/pipes.module";
import { LogoComponent } from "./logo/logo";
import { RegisterComponent } from "./register/register";
import { NewLogComponent } from './new-log/new-log';
import { LoaderComponent } from './loader/loader';
import { FormFieldComponent } from './form-field/form-field';

@NgModule({
  declarations: [
    AvatarComponent,
    AvatarMenuComponent,
    BrewLogComponent,
    LogoComponent,
    RegisterComponent,
    NewLogComponent,
    LoaderComponent,
    FormFieldComponent
  ],
  imports: [IonicModule, PipesModule],
  exports: [
    AvatarComponent,
    AvatarMenuComponent,
    BrewLogComponent,
    LogoComponent,
    RegisterComponent, NewLogComponent,
    LoaderComponent,
    FormFieldComponent
  ],
  entryComponents: [AvatarComponent, RegisterComponent, NewLogComponent]
})
export class ComponentsModule {}
