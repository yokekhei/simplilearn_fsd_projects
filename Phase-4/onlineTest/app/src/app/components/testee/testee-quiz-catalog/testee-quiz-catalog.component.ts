import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

import { Category } from 'src/app/model/category';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-testee-quiz-catalog',
  templateUrl: './testee-quiz-catalog.component.html',
  styleUrls: ['./testee-quiz-catalog.component.scss']
})
export class TesteeQuizCatalogComponent implements OnInit, OnDestroy {

  category: Category;
  categories: Category[] = [];
  subscriptionCategories: Subscription;

  constructor(private activatedRoute: ActivatedRoute, private dataService: DataService) {
    this.category = { id: 0, name: 'Default Category' };
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories);
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (this.categories.length > 0) {
        const result: Category[] = this.categories.filter(
          category => category.id === +params.id);

        if (result.length > 0) {
          this.category = result[0];
        }
      }
    });
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
