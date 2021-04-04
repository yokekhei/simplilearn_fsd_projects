import { Address } from './../../../models/address';
import { User } from './../../../models/user';
import { Common } from 'src/app/core/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

import { AuthenticationService } from './../../../services/authentication.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.scss']
})
export class UserRegisterComponent implements OnInit {

  private userEmail = '';

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
  }

  submit(form: NgForm): void {
    const formAddress: Address = {
      line1: form.form.value.addressLine1,
      line2: form.form.value.addressLine2,
      city: form.form.value.city,
      postcode: form.form.value.postcode,
    };

    this.authService.register({
      email: form.form.value.email,
      password: form.form.value.password,
      username: form.form.value.username,
      phone: form.form.value.phone,
      role: Common.ROLE_USER,
      address: formAddress
    }).subscribe(
      (user: User) => this.userEmail = user.email,
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => {
        this.router.navigate(['/user/login'],
          { queryParams: { registered: 1, email: this.userEmail } });
      }
    );
  }

}
