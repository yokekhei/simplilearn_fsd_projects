import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { AuthenticationService } from './../../../services/authentication.service';
import { CartService } from './../../../services/cart.service';
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
  private subscriptionCart: Subscription;

  constructor(private authService: AuthenticationService, private dataService: DataService,
              private cartService: CartService, private router: Router) {
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
          this.userName = loginUser.username;
        }
      }
    );

    this.subscriptionCart = this.dataService.userCartInfo.subscribe(
      cart => this.setCartItemNo());
  }

  ngOnInit(): void {
    this.setCartItemNo();
  }

  setCartItemNo(): void {
    this.cartItemNo = 0;

    const cartDetails = this.cartService.cartDetails;
    if (cartDetails !== null) {
      cartDetails.items.forEach(item => this.cartItemNo += item.quantity);
    }
  }

  search(event: any): void {
    if (event.key === 'Enter') {
      this.router.navigate([`/user/food/search/${event.target.value}`]);
    }
  }

  ngOnDestroy(): void {
    this.subscriptionLoginUser.unsubscribe();
    this.subscriptionCart.unsubscribe();
  }

}
