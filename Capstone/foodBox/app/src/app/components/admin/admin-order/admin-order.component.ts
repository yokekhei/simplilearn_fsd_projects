import { ActivatedRoute } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { CategoryService } from 'src/app/services/category.service';
import { OrderService } from './../../../services/order.service';
import { FoodService } from './../../../services/food.service';
import { DataService } from 'src/app/services/data.service';
import { Category } from './../../../models/category';
import { Order } from 'src/app/models/order';
import { User } from './../../../models/user';
import { UserService } from './../../../services/user.service';

@Component({
  selector: 'app-admin-order',
  templateUrl: './admin-order.component.html',
  styleUrls: ['./admin-order.component.scss']
})
export class AdminOrderComponent implements OnInit, OnDestroy {

  order?: Order;
  user?: User;
  private categories: Category[] = [];
  private subscriptionCategories: Subscription;

  constructor(private dataService: DataService,
              private foodService: FoodService,
              private orderService: OrderService,
              private categoryService: CategoryService,
              private userService: UserService,
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

          this.userService.getUserById(this.order.userId).subscribe(
            user => this.user = user);
        });
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
