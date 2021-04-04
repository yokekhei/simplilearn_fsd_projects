import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

import { Address } from './../../../models/address';
import { Common } from 'src/app/core/common';
import { User } from './../../../models/user';
import { UserService } from './../../../services/user.service';

@Component({
  selector: 'app-admin-user-report',
  templateUrl: './admin-user-report.component.html',
  styleUrls: ['./admin-user-report.component.scss']
})
export class AdminUserReportComponent implements OnInit {

  users: User[] = [];
  startDate = '';
  endDate = '';
  defaultDaysNum = 6;

  constructor(private userService: UserService) {
    const previousDay: Date = new Date();
    previousDay.setDate(previousDay.getDate() - this.defaultDaysNum);
    const today: Date = new Date();

    this.startDate = Common.formatHtmlDate(previousDay);
    this.endDate = Common.formatHtmlDate(today);
  }

  ngOnInit(): void {
    this.getUserList();
  }

  getUserList(): void {
    const fromDate = Common.formatServiceDate(this.startDate);
    const toDate = Common.formatServiceDate(this.endDate);

    this.userService.getUsersBetweenDate(fromDate, toDate).subscribe(
      users => this.users = users.filter(user => user.role === Common.ROLE_USER));
  }

  getDate(strDate?: string): Date {
    if (strDate === undefined) {
      return new Date();
    }

    return new Date(strDate);
  }

  getAddress(address: Address | undefined): string {
    if (address === undefined || address === null) { return 'N/A'; }

    return address.line1 + ' ' + address.line2 + ' '
      + address.postcode + ' ' + address.city;
  }

  onFilter(form: NgForm): void {
    const from = form.form.value.fromDate;
    const to = form.form.value.toDate;

    if (from > to) {
      Swal.fire('Start date must not later than end date', '', 'error');
    }

    this.getUserList();
  }

}
