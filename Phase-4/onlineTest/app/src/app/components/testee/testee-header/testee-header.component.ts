import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { Category } from 'src/app/model/category';
import { DataService } from 'src/app/services/data.service';
import { CategoryService } from './../../../services/category.service';

@Component({
  selector: 'app-testee-header',
  templateUrl: './testee-header.component.html',
  styleUrls: ['./testee-header.component.scss']
})
export class TesteeHeaderComponent implements OnInit, OnDestroy {

  userName = 'Guest';
  isGuest: boolean;
  categories: Category[] = [];
  defaultCategoryId = 0;
  subscriptionCategories: Subscription;
  subscriptionDefaultCategoryId: Subscription;

  constructor(private categoryService: CategoryService, private dataService: DataService) {
    this.isGuest = true;
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories);
    this.subscriptionDefaultCategoryId = this.dataService.defaultCategoryId.subscribe(
      defaultCategoryId => this.defaultCategoryId = defaultCategoryId);
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(
      categories => {
        this.categories = categories;
        this.dataService.changeCategories(this.categories);
      }
    );
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
    this.subscriptionDefaultCategoryId.unsubscribe();
  }

}
