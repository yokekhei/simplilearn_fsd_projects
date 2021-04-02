import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ConfigurationService } from './configuration.service';
import { Order } from './../models/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private url = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.url = `${this.configuration.getValue('apiUrl')}/order`;
  }

  getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.url}`).pipe(
      map(orders => {
        return orders.map(order => {
          return {
            id: order.id,
            chargeId: order.chargeId,
            userId: order.userId,
            items: order.items,
            price: order.price,
            discount: order.discount,
            deliveryFee: order.deliveryFee,
            createdDateTime: order.createdDateTime
          };
        });
      })
    );
  }

  getOrdersByDays(days: number): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.url}/history/${days}`).pipe(
      map(orders => {
        return orders.map(order => {
          return {
            id: order.id,
            chargeId: order.chargeId,
            userId: order.userId,
            items: order.items,
            price: order.price,
            discount: order.discount,
            deliveryFee: order.deliveryFee,
            createdDateTime: order.createdDateTime
          };
        });
      })
    );
  }

  getOrderById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.url}/${id}`);
  }

  createOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(this.url, order);
  }

  updateOrder(order: Order): Observable<Order> {
    return this.http.put<Order>(this.url, order);
  }

  deleteOrder(id: number): Observable<Order> {
    return this.http.delete<Order>(`${this.url}/${id}`);
  }

}
