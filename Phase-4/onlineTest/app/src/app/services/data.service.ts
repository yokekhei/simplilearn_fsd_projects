import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

import { Category } from 'src/app/model/category';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private categoriesSource: BehaviorSubject<Category[]>;
  categories: Observable<Category[]>;

  private defaultCategoryIdSource: BehaviorSubject<number>;
  defaultCategoryId: Observable<number>;

  constructor() {
    this.categoriesSource = new BehaviorSubject([] as Category[]);
    this.categories = this.categoriesSource.asObservable();

    this.defaultCategoryIdSource = new BehaviorSubject(0);
    this.defaultCategoryId = this.defaultCategoryIdSource.asObservable();
  }

  changeCategories(categories: Category[]): void {
    this.categoriesSource.next(categories);
  }

  changeDefaultCategoryId(id: number): void {
    this.defaultCategoryIdSource.next(id);
  }

}
