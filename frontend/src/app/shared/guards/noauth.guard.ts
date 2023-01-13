import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class NoauthGuard implements CanActivate {

  constructor(private _location: Location, private authService: AuthService) {}

  canActivate() {
    if (!this.authService.getLoggedIn()) {
      return true;
    } else {
      this._location.back();
      return false;
    }
  }
}
