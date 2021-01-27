import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

import { Answer } from '../models/answer';
import { Category } from 'src/app/models/category';
import { LoginUser } from '../models/login-user';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private categoriesSource: BehaviorSubject<Category[]>;
  categories: Observable<Category[]>;

  private latestCategorySource: BehaviorSubject<Category>;
  latestCategory: Observable<Category>;

  private loginUserSource: BehaviorSubject<LoginUser>;
  loginUser: Observable<LoginUser>;

  private answerSource: BehaviorSubject<Answer>;
  answer: Observable<Answer>;

  constructor() {
    this.categoriesSource = new BehaviorSubject([] as Category[]);
    this.categories = this.categoriesSource.asObservable();

    this.latestCategorySource = new BehaviorSubject({} as Category);
    this.latestCategory = this.latestCategorySource.asObservable();

    this.loginUserSource = new BehaviorSubject({} as LoginUser);
    this.loginUser = this.loginUserSource.asObservable();

    this.answerSource = new BehaviorSubject({} as Answer);
    this.answer = this.answerSource.asObservable();
  }

  changeCategories(categories: Category[]): void {
    this.categoriesSource.next(categories);
  }

  changeLatestCategory(category: Category): void {
    this.latestCategorySource.next(category);
  }

  changeLoginUser(loginUser: LoginUser): void {
    this.loginUserSource.next(loginUser);
  }

  changeAnswer(answer: Answer): void {
    this.answerSource.next(answer);
  }

}
