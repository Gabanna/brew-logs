import { NgModule } from "@angular/core";
import { HomePage } from "./home";
import { IonicPageModule } from "ionic-angular";
import { ComponentsModule } from '../../components/components.module';
import { AvatarMenuComponent } from '../../components/avatar-menu/avatar-menu';
import { I18nPipe } from '../../pipes/i18n/i18n';
import { PipesModule } from '../../pipes/pipes.module';

@NgModule({
  declarations: [HomePage],
  imports: [IonicPageModule.forChild(HomePage), ComponentsModule, PipesModule],
  entryComponents: [HomePage, AvatarMenuComponent]
})
export class HomePageModule {}
