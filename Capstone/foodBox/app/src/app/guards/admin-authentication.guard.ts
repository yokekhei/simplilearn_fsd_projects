import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

import { AuthenticationService } from './../services/authentication.service';
import { Common } from 'src/app/core/common';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthenticationGuard implements CanActivate {

  constructor(private router: Router,
              private authService: AuthenticationService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.authService.isLoggedIn(Common.ROLE_ADMIN)) {
      return this.router.parseUrl('admin/login');
    }

    return true;
  }

}
