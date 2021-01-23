import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import swal from 'sweetalert';

import { Common } from 'src/app/core/common';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-tester-register',
  templateUrl: './tester-register.component.html',
  styleUrls: ['./tester-register.component.scss']
})
export class TesterRegisterComponent implements OnInit {

  private userEmail = '';

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  submit(form: NgForm): void {
    this.userService.register({
      email: form.form.value.email,
      password: form.form.value.password,
      username: form.form.value.username,
      role: Common.ROLE_TESTER
    }).subscribe(
      (user: User) => this.userEmail = user.email,
      (err: any) => swal(err.error.message, '', 'error'),
      () => {
        this.router.navigate(['/tester/login'],
          { queryParams: { registered: 1, email: this.userEmail } });
      }
    );
  }

}
