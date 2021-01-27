import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import swal from 'sweetalert';

import { Common } from 'src/app/core/common';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-testee-register',
  templateUrl: './testee-register.component.html',
  styleUrls: ['./testee-register.component.scss']
})
export class TesteeRegisterComponent implements OnInit {

  private userEmail = '';

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  submit(form: NgForm): void {
    this.userService.register({
      email: form.form.value.email,
      password: form.form.value.password,
      username: form.form.value.username,
      role: Common.ROLE_TESTEE
    }).subscribe(
      (user: User) => this.userEmail = user.email,
      (err: any) => swal(err.error.message, '', 'error'),
      () => {
        this.router.navigate(['/testee/login'],
          { queryParams: { registered: 1, email: this.userEmail } });
      }
    );
  }

}
