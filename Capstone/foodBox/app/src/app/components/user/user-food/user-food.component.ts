import { ActivatedRoute } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { Category } from './../../../models/category';
import { DataService } from 'src/app/services/data.service';
import { Food } from './../../../models/food';
import { FoodService } from './../../../services/food.service';

@Component({
  selector: 'app-user-food',
  templateUrl: './user-food.component.html',
  styleUrls: ['./user-food.component.scss']
})
export class UserFoodComponent implements OnInit, OnDestroy {

  food?: Food;
  categories: Category[] = [];
  private subscriptionCategories: Subscription;

  constructor(private foodService: FoodService, private dataService: DataService,
              private activatedRoute: ActivatedRoute) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories);
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params.id !== undefined) {
        this.foodService.getFoodById(+params.id).subscribe(food => this.food = food);
      }
    });
  }

  getFoodImageUrl(foodId: number | undefined): string {
    if (foodId === undefined) { return ''; }

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

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
