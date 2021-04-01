import { BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';

import { Cart } from '../models/cart';
import { Category } from './../models/category';
import { LoginUser } from './../models/login-user';
import { Offer } from './../models/offer';
import { PageInfo } from './../models/page-info';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private categoriesSource: BehaviorSubject<Category[]>;
  categories: Observable<Category[]>;

  private offersSource: BehaviorSubject<Offer[]>;
  offers: Observable<Offer[]>;

  private loginUserSource: BehaviorSubject<LoginUser>;
  loginUser: Observable<LoginUser>;

  private adminFoodPageInfoSource: BehaviorSubject<PageInfo>;
  adminFoodPageInfo: Observable<PageInfo>;

  private userFoodPageInfoSource: BehaviorSubject<PageInfo>;
  userFoodPageInfo: Observable<PageInfo>;

  private userCartSource: BehaviorSubject<Cart | null>;
  userCartInfo: Observable<Cart | null>;

  constructor() {
    this.categoriesSource = new BehaviorSubject([] as Category[]);
    this.categories = this.categoriesSource.asObservable();

    this.offersSource = new BehaviorSubject([] as Offer[]);
    this.offers = this.offersSource.asObservable();

    this.loginUserSource = new BehaviorSubject({} as LoginUser);
    this.loginUser = this.loginUserSource.asObservable();

    this.adminFoodPageInfoSource = new BehaviorSubject({} as PageInfo);
    this.adminFoodPageInfo = this.adminFoodPageInfoSource.asObservable();

    this.userFoodPageInfoSource = new BehaviorSubject({} as PageInfo);
    this.userFoodPageInfo = this.userFoodPageInfoSource.asObservable();

    this.userCartSource = new BehaviorSubject({} as Cart | null);
    this.userCartInfo = this.userCartSource.asObservable();
  }

  changeCategories(categories: Category[]): void {
    this.categoriesSource.next(categories);
  }

  changeOffers(offers: Offer[]): void {
    this.offersSource.next(offers);
  }

  changeLoginUser(loginUser: LoginUser): void {
    this.loginUserSource.next(loginUser);
  }

  changeAdminFoodPageInfo(pageInfo: PageInfo): void {
    this.adminFoodPageInfoSource.next(pageInfo);
  }

  changeUserFoodPageInfo(pageInfo: PageInfo): void {
    this.userFoodPageInfoSource.next(pageInfo);
  }

  changeUserCartInfo(cart: Cart | null): void {
    this.userCartSource.next(cart);
  }

}
