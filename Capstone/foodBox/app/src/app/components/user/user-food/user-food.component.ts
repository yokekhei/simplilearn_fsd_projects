import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { Cart } from './../../../models/cart';
import { CartItem } from './../../../models/cart-item';
import { CartService } from './../../../services/cart.service';
import { Category } from './../../../models/category';
import { Common } from 'src/app/core/common';
import { DataService } from 'src/app/services/data.service';
import { Food } from './../../../models/food';
import { FoodService } from './../../../services/food.service';
import { Offer } from './../../../models/offer';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-user-food',
  templateUrl: './user-food.component.html',
  styleUrls: ['./user-food.component.scss']
})
export class UserFoodComponent implements OnInit, OnDestroy {

  food?: Food;
  categories: Category[] = [];
  offers: Offer[] = [];
  private discount = 0.0;
  private subscriptionCategories: Subscription;

  constructor(private foodService: FoodService, private dataService: DataService,
              private cartService: CartService, private offerService: OfferService,
              private activatedRoute: ActivatedRoute, private router: Router) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories);
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params.id !== undefined) {
        this.foodService.getFoodById(+params.id).subscribe(food => this.food = food);
      }
    });

    this.offerService.getAllOffers().subscribe(
      offers => {
        this.offers = offers;
        this.dataService.changeOffers(offers);
      }
    );
  }

  getFoodImageUrl(foodId: number | undefined): string {
    if (foodId === undefined || foodId === null) { return ''; }

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

  onAddToCart(): void {
    if (this.food === undefined) { return; }

    let cartDetails: Cart | null = this.cartService.cartDetails;

    if (cartDetails === null) {
      cartDetails = {
        items: [
          {
            food: this.food,
            quantity: 1,
            totalPrice: this.food.price,
            discount: this.discount
          }
        ],
        subTotalPrice: this.food.price,
        totalDiscount: this.discount,
        totalPrice: this.food.price - this.discount
      };
    } else {
      const cartItems: CartItem[] = cartDetails.items.filter(
        item => item.food.id === this.food?.id);

      if (cartItems.length > 0) {
        cartItems[0].quantity += 1;
        cartItems[0].totalPrice += this.food.price;
        cartItems[0].discount += this.discount;
      } else {
        cartDetails.items.push({
          food: this.food,
          quantity: 1,
          totalPrice: this.food.price,
          discount: this.discount
        });
      }
    }

    this.cartService.cartDetails = cartDetails;
    this.dataService.changeUserCartInfo(cartDetails);
    this.router.navigate(['/user/cart']);
  }

  hasDiscount(offerId: number | null | undefined): boolean {
    if (offerId === undefined || offerId === null ||
        this.offers.length === 0) { return false; }

    return true;
  }

  getOfferDesc(offerId: number | null | undefined): string {
    if (offerId === undefined || offerId === null ||
        this.offers.length === 0 || this.food === undefined) { return ''; }

    const ofs = this.offers.filter(offer => offer.id === offerId);
    if (ofs.length > 0) {
      if (ofs[0].discountType === Common.OFFER_TYPE_PCT) {
        this.discount = this.food.price * ofs[0].discount / 100;
        return 'DISCOUNT ' + ofs[0].discount + '%';
      } else if (ofs[0].discountType === Common.OFFER_TYPE_CSH) {
        this.discount = ofs[0].discount;
        return 'DISCOUNT $' + ofs[0].discount;
      }
    }

    return '';
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
