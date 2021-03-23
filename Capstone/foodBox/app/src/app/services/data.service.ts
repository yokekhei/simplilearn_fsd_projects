import { BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';

import { Category } from './../models/category';
import { LoginUser } from './../models/login-user';
import { PageInfo } from './../models/page-info';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private categoriesSource: BehaviorSubject<Category[]>;
  categories: Observable<Category[]>;

  private loginUserSource: BehaviorSubject<LoginUser>;
  loginUser: Observable<LoginUser>;

  private adminFoodPageInfoSource: BehaviorSubject<PageInfo>;
  adminFoodPageInfo: Observable<PageInfo>;

  constructor() {
    this.categoriesSource = new BehaviorSubject([] as Category[]);
    this.categories = this.categoriesSource.asObservable();

    this.loginUserSource = new BehaviorSubject({} as LoginUser);
    this.loginUser = this.loginUserSource.asObservable();

    this.adminFoodPageInfoSource = new BehaviorSubject({} as PageInfo);
    this.adminFoodPageInfo = this.adminFoodPageInfoSource.asObservable();
  }

  changeCategories(categories: Category[]): void {
    this.categoriesSource.next(categories);
  }

  changeLoginUser(loginUser: LoginUser): void {
    this.loginUserSource.next(loginUser);
  }

  changeAdminFoodPageInfo(pageInfo: PageInfo): void {
    this.adminFoodPageInfoSource.next(pageInfo);
  }

}
