import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

import { Answer } from '../model/answer';
import { Category } from 'src/app/model/category';
import { Common } from 'src/app/core/common';
import { LoginUser } from '../model/login-user';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private categoriesSource: BehaviorSubject<Category[]>;
  categories: Observable<Category[]>;

  private latestCategoryIdSource: BehaviorSubject<number>;
  latestCategoryId: Observable<number>;

  private loginUserSource: BehaviorSubject<LoginUser>;
  loginUser: Observable<LoginUser>;

  private answerSource: BehaviorSubject<Answer>;
  answer: Observable<Answer>;

  constructor() {
    this.categoriesSource = new BehaviorSubject([] as Category[]);
    this.categories = this.categoriesSource.asObservable();

    this.latestCategoryIdSource = new BehaviorSubject(Common.UNKNOWN_CATEGORY_ID);
    this.latestCategoryId = this.latestCategoryIdSource.asObservable();

    this.loginUserSource = new BehaviorSubject({} as LoginUser);
    this.loginUser = this.loginUserSource.asObservable();

    this.answerSource = new BehaviorSubject({} as Answer);
    this.answer = this.answerSource.asObservable();
  }

  changeCategories(categories: Category[]): void {
    this.categoriesSource.next(categories);
  }

  changeLatestCategoryId(id: number): void {
    this.latestCategoryIdSource.next(id);
  }

  changeLoginUser(loginUser: LoginUser): void {
    this.loginUserSource.next(loginUser);
  }

  changeAnswer(answer: Answer): void {
    this.answerSource.next(answer);
  }

}
