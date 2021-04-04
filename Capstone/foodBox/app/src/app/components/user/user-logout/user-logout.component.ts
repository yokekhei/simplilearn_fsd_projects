import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../../../services/authentication.service';
import { CartService } from './../../../services/cart.service';
import { Common } from 'src/app/core/common';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-user-logout',
  templateUrl: './user-logout.component.html',
  styleUrls: ['./user-logout.component.scss']
})
export class UserLogoutComponent implements OnInit {

  constructor(private dataService: DataService,
              private authService: AuthenticationService,
              private cartService: CartService,
              private router: Router) { }

  ngOnInit(): void {
    this.cartService.reset();
    this.dataService.changeUserCartInfo(null);
    this.authService.removeSession();

    this.dataService.changeLoginUser(
      { email: '', username: Common.GUEST_NAME, role: Common.ROLE_USER });
    this.router.navigate(['/user/login']);
  }

}
