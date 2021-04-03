import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

import { Common } from 'src/app/core/common';
import { Order } from 'src/app/models/order';
import { OrderService } from './../../../services/order.service';

@Component({
  selector: 'app-admin-order-report',
  templateUrl: './admin-order-report.component.html',
  styleUrls: ['./admin-order-report.component.scss']
})
export class AdminOrderReportComponent implements OnInit {

  orders: Order[] = [];
  startDate = '';
  endDate = '';
  defaultDaysNum = 6;

  constructor(private orderService: OrderService) {
    const previousDay: Date = new Date();
    previousDay.setDate(previousDay.getDate() - this.defaultDaysNum);
    const today: Date = new Date();

    this.startDate = this.formatHtmlDate(previousDay);
    this.endDate = this.formatHtmlDate(today);
  }

  ngOnInit(): void {
    this.getOrderList();
  }

  getOrderList(): void {
    const fromDate = this.formatServiceDate(this.startDate);
    const toDate = this.formatServiceDate(this.endDate);

    this.orderService.getOrdersBetweenDate(fromDate, toDate).subscribe(
      orders => this.orders = orders);
  }

  getDate(strDate?: string): Date {
    if (strDate === undefined) {
      return new Date();
    }

    return new Date(strDate);
  }

  onFilter(form: NgForm): void {
    const from = form.form.value.fromDate;
    const to = form.form.value.toDate;

    if (from > to) {
      Swal.fire('Start date must not later than end date', '', 'error');
    }

    this.getOrderList();
  }

  formatHtmlDate(localeDate: Date): string {
    // M/d/yyyy to yyyy-MM-dd
    const localeDateStr = localeDate.toLocaleDateString();
    const inputs: string[] = localeDateStr.split('/');

    const month = Common.paddy(+inputs[0], 2, '0');
    const day = Common.paddy(+inputs[1], 2, '0');
    const year = inputs[2];

    return `${year}-${month}-${day}`;
  }

  formatServiceDate(htmlDate: string): string {
    // yyyy-MM-dd to dd-MM-yyyy
    const inputs: string[] = htmlDate.split('-');
    const year = inputs[0];
    const month = inputs[1];
    const day = inputs[2];

    return `${day}-${month}-${year}`;
  }

}
