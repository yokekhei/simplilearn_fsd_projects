import { Component, OnInit } from '@angular/core';

import { AuthenticationService } from './../../../services/authentication.service';
import { Order } from './../../../models/order';
import { OrderService } from './../../../services/order.service';

@Component({
  selector: 'app-user-order-history',
  templateUrl: './user-order-history.component.html',
  styleUrls: ['./user-order-history.component.scss']
})
export class UserOrderHistoryComponent implements OnInit {

  numOfDays = 7;
  orders: Order[] = [];
  private userEmail = '';

  constructor(private orderService: OrderService, private authService: AuthenticationService) {
    this.userEmail = this.authService.loginUser?.email || '';
  }

  ngOnInit(): void {
    this.getOrders();
  }

  getOrders(): void {
    this.orderService.getOrdersByUserAndDays(this.userEmail, this.numOfDays).subscribe(
      orders => this.orders = orders);
  }

  getDate(strDate?: string): Date {
    if (strDate === undefined) {
      return new Date();
    }

    return new Date(strDate);
  }

  onChangeNumberOfDays(event: any): void {
    if (event !== undefined &&
      event.target !== undefined &&
      event.target.value !== undefined) {
      this.numOfDays = event.target.value;
      this.getOrders();
    }
  }

}
