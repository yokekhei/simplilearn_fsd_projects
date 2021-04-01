import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { StripeCardComponent, StripeService } from 'ngx-stripe';
import { StripeCardElementOptions, StripeElementsOptions } from '@stripe/stripe-js';
import Swal from 'sweetalert2';

import { AuthenticationService } from './../../../services/authentication.service';
import { Cart } from 'src/app/models/cart';
import { CartService } from './../../../services/cart.service';
import { ChargeRequest } from './../../../models/charge-request';
import { ChargeResponse } from './../../../models/charge-response';
import { ChargeService } from './../../../services/charge.service';
import { Common } from 'src/app/core/common';
import { LoginUser } from './../../../models/login-user';

@Component({
  selector: 'app-user-checkout',
  templateUrl: './user-checkout.component.html',
  styleUrls: ['./user-checkout.component.scss']
})
export class UserCheckoutComponent implements OnInit {

  private payorName = '';
  private currency = Common.CURRENCY_MYR;
  loginUser: LoginUser | null;
  cartDetails: Cart | null;
  cardErrorMessage: string | undefined = '';
  cardOptions: StripeCardElementOptions = {
    style: {
      base: {
        iconColor: '#666EE8',
        color: '#31325F',
        fontWeight: '300',
        fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
        fontSize: '18px',
        '::placeholder': {
          color: '#CFD7E0'
        }
      }
    }
  };
  elementsOptions: StripeElementsOptions = {
    locale: 'en'
  };

  @ViewChild(StripeCardComponent) card?: StripeCardComponent;

  constructor(private authService: AuthenticationService,
              private cartService: CartService,
              private stripeService: StripeService,
              private chargeService: ChargeService) {
    this.loginUser = this.authService.loginUser;
    this.cartDetails = this.cartService.cartDetails;
  }

  ngOnInit(): void {
  }

  createToken(): void {
    if (this.card === undefined ||
      this.cartDetails === null ||
      this.cartDetails.totalPrice === undefined) { return; }

    this.stripeService
      .createToken(this.card.element, { name: this.payorName, currency: this.currency })
      .subscribe(result => {
        if (result.token) {
          this.cardErrorMessage = '';

          const chargeRequest: ChargeRequest = {
            description: '[Foodbox] Payment from ' + this.loginUser?.username,
            amount: this.cartDetails?.totalPrice || 0.0,
            currency: this.currency,
            stripeEmail: this.loginUser?.email || '',
            stripeToken: result.token.id
          };

          this.chargeService.createCharge(chargeRequest).subscribe(
            (chargeResponse: ChargeResponse) => {
              console.log(chargeResponse);
            },
            (err: any) => Swal.fire(err.error.message, '', 'error'),
          );
        } else if (result.error) {
          this.cardErrorMessage = result.error.message || '';
        }
      });
  }

  onPlaceOrder(form: NgForm): void {
    this.payorName = form.form.value.payorName;
    this.createToken();
  }

}
