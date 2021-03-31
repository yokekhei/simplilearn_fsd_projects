import { Injectable } from '@angular/core';

import { Cart } from './../models/cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor() { }

  get cartDetails(): Cart | null {
    if (sessionStorage.cartDetails) {
      return JSON.parse(sessionStorage.cartDetails);
    }

    return null;
  }

  set cartDetails(o: Cart | null) {
    sessionStorage.cartDetails = JSON.stringify(o);
  }

}
