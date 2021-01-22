import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import swal from 'sweetalert';

import { Common } from 'src/app/core/common';
import { DataService } from 'src/app/services/data.service';
import { LoginUser } from './../../../model/login-user';
import { User } from './../../../model/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-testee-login',
  templateUrl: './testee-login.component.html',
  styleUrls: ['./testee-login.component.scss']
})
export class TesteeLoginComponent implements OnInit, OnDestroy {

  private loginUser: LoginUser;
  private subscriptionDefaultCategoryId: Subscription;
  private defaultCategoryId = 0;

  constructor(private userService: UserService, private dataService: DataService, private router: Router) {
    this.loginUser = { email: '', username: Common.GUEST_NAME, role: Common.ROLE_TESTEE };
    this.subscriptionDefaultCategoryId = this.dataService.defaultCategoryId.subscribe(
      defaultCategoryId => this.defaultCategoryId = defaultCategoryId);
  }

  ngOnInit(): void {
  }

  submit(form: NgForm): void {
    this.userService.login({
      email: form.form.value.email,
      password: form.form.value.password,
      role: Common.ROLE_TESTEE
    }).subscribe(
      (user: User) => {
        this.loginUser.email = user.email;
        this.loginUser.username = user.username || Common.GUEST_NAME;
        this.loginUser.role = user.role;
        sessionStorage.loginUser = JSON.stringify(this.loginUser);
      },
      (err: any) => swal(err.error.message, '', 'error'),
      () => {
        this.dataService.changeLoginUser(this.loginUser);
        this.router.navigate([`/testee/category/${this.defaultCategoryId}`]);
      }
    );
  }

  ngOnDestroy(): void {
    this.subscriptionDefaultCategoryId.unsubscribe();
  }

}
