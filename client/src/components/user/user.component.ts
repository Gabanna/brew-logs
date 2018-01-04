import { AuthService } from './../../services/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'user',
  templateUrl: 'user.component.html'
})
export class UserComponent   {

  constructor(
      public authService: AuthService
  ) {}
}
