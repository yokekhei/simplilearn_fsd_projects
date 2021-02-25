import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { AuthenticationService } from './../../../services/authentication.service';
import { Common } from 'src/app/core/common';
import { DataService } from './../../../services/data.service';
import { LoginUser } from './../../../models/login-user';

@Component({
  selector: 'app-user-header',
  templateUrl: './user-header.component.html',
  styleUrls: ['./user-header.component.scss']
})
export class UserHeaderComponent implements OnInit, OnDestroy {

  userName = Common.GUEST_NAME;
  isGuest = true;
  cartItemNo = 0;
  private loginUser: LoginUser;
  private subscriptionLoginUser: Subscription;

  constructor(private authService: AuthenticationService, private dataService: DataService) {
    if (this.authService.isLoggedIn(Common.ROLE_USER)) {
      this.loginUser = this.authService.loginUser ||
        { email: '', username: '', role: Common.ROLE_USER };
      this.isGuest = false;
    } else {
      this.loginUser = { email: '', username: Common.GUEST_NAME, role: Common.ROLE_USER };
      this.isGuest = true;
    }

    this.userName = this.loginUser.username;

    this.subscriptionLoginUser = this.dataService.loginUser.subscribe(
      loginUser => {
        if (loginUser.username && loginUser.username !== this.userName) {
          this.isGuest = this.authService.isLoggedIn(Common.ROLE_USER) ? false : true;

          if (!this.isGuest) {
            this.userName = loginUser.username;
          }
        }
      }
    );
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.subscriptionLoginUser.unsubscribe();
  }

}
