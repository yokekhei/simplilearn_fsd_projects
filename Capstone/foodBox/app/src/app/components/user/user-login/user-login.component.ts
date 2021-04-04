import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

import { AuthenticationService } from '../../../services/authentication.service';
import { CartService } from './../../../services/cart.service';
import { Common } from 'src/app/core/common';
import { DataService } from '../../../services/data.service';
import { LoginUser } from '../../../models/login-user';
import { User } from '../../../models/user';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit {

  userEmail = '';
  registered = false;

  private loginUser: LoginUser;

  constructor(private authService: AuthenticationService,
              private dataService: DataService,
              private cartService: CartService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
    this.loginUser = { email: '', username: Common.GUEST_NAME, role: Common.ROLE_USER };
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(
      qs => {
        this.userEmail = qs.email;
        this.registered = qs.registered;
      });
  }

  submit(form: NgForm): void {
    this.authService.login({
      email: form.form.value.email,
      password: form.form.value.password,
      role: Common.ROLE_USER
    }).subscribe(
      (user: User) => {
        this.loginUser.email = user.email;
        this.loginUser.username = user.username || user.email;
        this.loginUser.role = user.role;
        this.loginUser.address = user.address;
        this.loginUser.phone = user.phone;
        this.authService.loginUser = this.loginUser;
      },
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => {
        this.dataService.changeLoginUser(this.loginUser);

        if (this.cartService.cartDetails !== null) {
          this.router.navigate(['/user/cart']);
        } else {
          this.router.navigate(['/user/category']);
        }
      }
    );
  }

}
