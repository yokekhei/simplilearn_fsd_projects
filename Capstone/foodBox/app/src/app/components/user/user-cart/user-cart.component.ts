import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { Cart } from 'src/app/models/cart';
import { CartItem } from './../../../models/cart-item';
import { CartService } from './../../../services/cart.service';
import { Category } from './../../../models/category';
import { CategoryService } from 'src/app/services/category.service';
import { Common } from 'src/app/core/common';
import { DataService } from './../../../services/data.service';
import { Fee } from './../../../models/fee';
import { FeeService } from './../../../services/fee.service';
import { Food } from './../../../models/food';
import { FoodService } from './../../../services/food.service';
import { Offer } from './../../../models/offer';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-user-cart',
  templateUrl: './user-cart.component.html',
  styleUrls: ['./user-cart.component.scss']
})
export class UserCartComponent implements OnInit, OnDestroy {

  cartDetails: Cart | null;
  deliveryFee: Fee = { type: 'delivery', value: 0.0 };
  private categories: Category[] = [];
  private offers: Offer[] = [];
  private subscriptionCategories: Subscription;

  constructor(private cartService: CartService, private foodService: FoodService,
              private offerService: OfferService, private feeService: FeeService,
              private dataService: DataService, private categoryService: CategoryService,
              private router: Router) {
    this.cartDetails = this.cartService.cartDetails;

    if (this.cartDetails === null) {
      this.cartDetails = {
        items: [],
        subTotalPrice: 0.0,
        totalDiscount: 0.0,
        deliveryFee: 0.0,
        totalPrice: 0.0
      };
    }

    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => {
        if (categories.length === 0) {
          this.categoryService.getAllCategories().subscribe(
            ctgrs => {
              this.categories = ctgrs;
              this.dataService.changeCategories(ctgrs);
            }
          );
        } else {
          this.categories = categories;
        }
      }
    );
  }

  ngOnInit(): void {
    this.offerService.getAllOffers().subscribe(
      offers => {
        this.offers = offers;
        this.dataService.changeOffers(offers);
      }
    );

    this.feeService.getDeliveryFee().subscribe(fee => {
      this.deliveryFee = fee;
      this.calculate();
    });
  }

  getFoodImageUrl(foodId: number | undefined): string {
    if (foodId === undefined) { return ''; }

    return this.foodService.getFoodImageUrl(foodId);
  }

  getCategoryName(categoryId: number | undefined): string {
    if (categoryId === undefined || this.categories.length === 0) { return ''; }

    const names = this.categories.filter(c => c.id === categoryId);
    if (names.length > 0) {
      return names[0].name;
    }

    return '';
  }

  onUpdateItem(event: any, food: Food | undefined): void {
    if (food === undefined || event === undefined ||
      event.target === undefined ||
      event.target.value === undefined ||
      this.cartDetails === null) { return; }

    const cartItems: CartItem[] = this.cartDetails.items.filter(
      item => item.food.id === food.id);

    if (cartItems.length > 0) {
      cartItems[0].quantity = +event.target.value;
      cartItems[0].totalPrice = cartItems[0].quantity * food.price;
      cartItems[0].discount = cartItems[0].quantity * this.getFoodDiscount(food);
      this.calculate();
    }
  }

  onRemoveItem(foodId: number | undefined): void {
    if (this.cartDetails === null || foodId === undefined) { return; }

    const cartItems: CartItem[] = this.cartDetails.items.filter(
      item => item.food.id === foodId);

    if (cartItems.length > 0) {
        const index = this.cartDetails.items.indexOf(cartItems[0], 0);

        if (index > -1) {
          this.cartDetails.items.splice(index, 1);
          this.calculate();
        }
      }
  }

  setSubTotalPrice(): void {
    if (this.cartDetails === null) { return; }

    let totalPrice = 0.0;
    this.cartDetails.items.forEach(item => totalPrice += item.totalPrice);
    this.cartDetails.subTotalPrice = totalPrice;
  }

  setDiscountPrice(): void {
    if (this.cartDetails === null) { return; }

    let totalDiscount = 0.0;
    this.cartDetails.items.forEach(item => totalDiscount += item.discount);
    this.cartDetails.totalDiscount = totalDiscount;
  }

  setDeliveryFee(): void {
    if (this.cartDetails === null) { return; }

    this.cartDetails.deliveryFee = this.getDeliveryFee();
  }

  setTotalPrice(): void {
    if (this.cartDetails === null ||
      this.cartDetails.subTotalPrice === undefined ||
      this.cartDetails.totalDiscount === undefined ||
      this.cartDetails.deliveryFee === undefined) { return; }

    this.cartDetails.totalPrice = this.cartDetails.subTotalPrice -
      this.cartDetails.totalDiscount + this.cartDetails.deliveryFee;
  }

  getFoodDiscount(food: Food | undefined): number {
    if (food === undefined) { return 0.0; }

    const ofs = this.offers.filter(offer => offer.id === food.offerId);
    let discount = 0.0;

    if (ofs.length > 0) {
      if (ofs[0].discountType === Common.OFFER_TYPE_PCT) {
        discount = food.price * ofs[0].discount / 100;
      } else if (ofs[0].discountType === Common.OFFER_TYPE_CSH) {
        discount = ofs[0].discount;
      }
    }

    return discount;
  }

  getDeliveryFee(): number {
    if (this.cartDetails === null || this.cartDetails.items.length === 0) { return 0.0; }

    return this.deliveryFee.value;
  }

  calculate(): void {
    if (this.cartDetails === null) { return; }

    this.setSubTotalPrice();
    this.setDiscountPrice();
    this.setDeliveryFee();
    this.setTotalPrice();
    this.cartService.cartDetails = this.cartDetails;
    this.dataService.changeUserCartInfo(this.cartDetails);
  }

  onCheckout(): void {
    this.router.navigate(['/user/checkout']);
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
