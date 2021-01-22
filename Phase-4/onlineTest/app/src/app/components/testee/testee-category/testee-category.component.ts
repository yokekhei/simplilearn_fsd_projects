import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { Category } from 'src/app/model/category';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-testee-category',
  templateUrl: './testee-category.component.html',
  styleUrls: ['./testee-category.component.scss']
})
export class TesteeCategoryComponent implements OnInit, OnDestroy {

  categories: Category[] = [];
  defaultCategoryId = 0;
  subscriptionCategories: Subscription;
  subscriptionDefaultCategoryId: Subscription;

  constructor(private dataService: DataService, private router: Router) {
    this.subscriptionDefaultCategoryId = this.dataService.defaultCategoryId.subscribe(
      defaultCategoryId => {
        this.defaultCategoryId = defaultCategoryId;
      }
    );

    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => {
        this.categories = categories;

        if (this.categories.length > 0) {
          this.dataService.changeDefaultCategoryId(this.categories[0].id);
          this.router.navigate([`/testee/category/${this.categories[0].id}`]);
        }
      });
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
    this.subscriptionDefaultCategoryId.unsubscribe();
  }

}
