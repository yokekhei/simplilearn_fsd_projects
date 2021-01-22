import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

import { Category } from 'src/app/model/category';
import { LoginUser } from './../model/login-user';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private categoriesSource: BehaviorSubject<Category[]>;
  categories: Observable<Category[]>;

  private defaultCategoryIdSource: BehaviorSubject<number>;
  defaultCategoryId: Observable<number>;

  private loginUserSource: BehaviorSubject<LoginUser>;
  loginUser: Observable<LoginUser>;

  constructor() {
    this.categoriesSource = new BehaviorSubject([] as Category[]);
    this.categories = this.categoriesSource.asObservable();

    this.defaultCategoryIdSource = new BehaviorSubject(0);
    this.defaultCategoryId = this.defaultCategoryIdSource.asObservable();

    this.loginUserSource = new BehaviorSubject({} as LoginUser);
    this.loginUser = this.loginUserSource.asObservable();
  }

  changeCategories(categories: Category[]): void {
    this.categoriesSource.next(categories);
  }

  changeDefaultCategoryId(id: number): void {
    this.defaultCategoryIdSource.next(id);
  }

  changeLoginUser(loginUser: LoginUser): void {
    this.loginUserSource.next(loginUser);
  }

}
