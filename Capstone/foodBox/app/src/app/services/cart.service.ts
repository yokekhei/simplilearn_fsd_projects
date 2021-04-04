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

  reset(): void {
    if (sessionStorage.cartDetails) {
      const cartInfo = {
        items: [],
        subTotalPrice: 0.0,
        totalDiscount: 0.0,
        deliveryFee: 0.0,
        totalPrice: 0.0
      };

      sessionStorage.cartDetails = JSON.stringify(cartInfo);
    }
  }

}
