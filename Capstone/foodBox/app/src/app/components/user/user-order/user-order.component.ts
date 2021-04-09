import { ActivatedRoute } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { Category } from './../../../models/category';
import { CategoryService } from 'src/app/services/category.service';
import { DataService } from 'src/app/services/data.service';
import { FoodService } from './../../../services/food.service';
import { Order } from './../../../models/order';
import { OrderService } from './../../../services/order.service';

@Component({
  selector: 'app-user-order',
  templateUrl: './user-order.component.html',
  styleUrls: ['./user-order.component.scss']
})
export class UserOrderComponent implements OnInit, OnDestroy {

  confirmed = false;
  order?: Order;
  orderLink: string[] = [];
  private categories: Category[] = [];
  private subscriptionCategories: Subscription;

  constructor(private dataService: DataService,
              private foodService: FoodService,
              private orderService: OrderService,
              private categoryService: CategoryService,
              private activatedRoute: ActivatedRoute) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => {
        if (categories.length === 0) {
          this.categoryService.getAllCategories().subscribe(
            ctgrs => {
              this.categories = ctgrs;
              this.dataService.changeCategories(ctgrs);
            }
          );
        } else {
          this.categories = categories;
        }
      });
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params.id !== undefined) {
        this.orderService.getOrderById(+params.id).subscribe(order => {
          this.order = order;
          this.orderLink = [`/user/order/${ this.order.id }`];
        });
      }
    });

    this.activatedRoute.queryParams.subscribe(
      qs => {
        this.confirmed = qs.confirmed;
      }
    );
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

  getSubTotalPrice(): number {
    if (this.order === undefined) { return 0.0; }

    let total = 0.0;
    this.order.items.forEach(item => total += item.price);
    return total;
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
