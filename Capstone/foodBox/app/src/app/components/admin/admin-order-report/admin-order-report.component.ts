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

    this.startDate = Common.formatHtmlDate(previousDay);
    this.endDate = Common.formatHtmlDate(today);
  }

  ngOnInit(): void {
    this.getOrderList();
  }

  getOrderList(): void {
    const fromDate = Common.formatServiceDate(this.startDate);
    const toDate = Common.formatServiceDate(this.endDate);

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

}
