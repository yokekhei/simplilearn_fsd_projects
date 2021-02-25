import { User } from './../../../models/user';
import { Common } from 'src/app/core/common';
import { DataService } from './../../../services/data.service';
import { AuthenticationService } from './../../../services/authentication.service';
import { LoginUser } from './../../../models/login-user';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent implements OnInit {

  userEmail = '';
  registered = false;

  private loginUser: LoginUser;

  constructor(private activatedRoute: ActivatedRoute,
              private authService: AuthenticationService,
              private dataService: DataService,
              private router: Router) {
    this.loginUser = { email: '', username: '', role: Common.ROLE_ADMIN };
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(
      qs => {
        this.userEmail = qs.email;
        this.registered = qs.registered;
      });

    if (this.authService.isLoggedIn(Common.ROLE_ADMIN)) {
      this.router.navigate(['/admin/home']);
    }
  }

  submit(form: NgForm): void {
    this.authService.login({
      email: form.form.value.email,
      password: form.form.value.password,
      role: Common.ROLE_ADMIN
    }).subscribe(
      (user: User) => {
        this.loginUser.email = user.email;
        this.loginUser.username = user.username || user.email;
        this.loginUser.role = user.role;
        this.authService.loginUser = this.loginUser;
      },
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => {
        this.dataService.changeLoginUser(this.loginUser);
        this.router.navigate(['/admin/home']);
      }
    );
  }

}
