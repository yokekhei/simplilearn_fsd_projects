import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import swal from 'sweetalert';

import { Common } from 'src/app/core/common';
import { LoginUser } from 'src/app/model/login-user';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-tester-login',
  templateUrl: './tester-login.component.html',
  styleUrls: ['./tester-login.component.scss']
})
export class TesterLoginComponent implements OnInit {

  userEmail = '';
  registered = false;

  private loginUser: LoginUser;

  constructor(private activatedRoute: ActivatedRoute,
              private userService: UserService,
              private dataService: DataService,
              private router: Router) {
    this.loginUser = { email: '', username: '', role: Common.ROLE_TESTER };
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(
      qs => {
        this.userEmail = qs.email;
        this.registered = qs.registered;
      });
  }

  submit(form: NgForm): void {
    this.userService.login({
      email: form.form.value.email,
      password: form.form.value.password,
      role: Common.ROLE_TESTER
    }).subscribe(
      (user: User) => {
        this.loginUser.email = user.email;
        this.loginUser.username = user.username || user.email;
        this.loginUser.role = user.role;
        sessionStorage.loginUser = JSON.stringify(this.loginUser);
      },
      (err: any) => swal(err.error.message, '', 'error'),
      () => {
        this.dataService.changeLoginUser(this.loginUser);
        this.router.navigate(['/tester/home']);
      }
    );
  }

}
