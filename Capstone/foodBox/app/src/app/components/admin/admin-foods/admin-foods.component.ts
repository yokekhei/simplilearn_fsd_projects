import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import Swal from 'sweetalert2';

import { Category } from './../../../models/category';
import { CategoryService } from 'src/app/services/category.service';
import { Common } from 'src/app/core/common';
import { DataService } from './../../../services/data.service';
import { Food } from './../../../models/food';
import { FoodService } from './../../../services/food.service';
import { Offer } from './../../../models/offer';
import { OfferService } from 'src/app/services/offer.service';
import { PageInfo } from './../../../models/page-info';

@Component({
  selector: 'app-admin-foods',
  templateUrl: './admin-foods.component.html',
  styleUrls: ['./admin-foods.component.scss']
})
export class AdminFoodsComponent implements OnInit, OnDestroy {

  foods: Food[] = [];
  pageInfo: PageInfo;
  entriesNum = 4;
  private categories: Category[] = [];
  private offers: Offer[] = [];
  private subscriptionPageInfo: Subscription;
  private subscriptionCategories: Subscription;
  private subscriptionOffers: Subscription;

  constructor(private foodService: FoodService, private dataService: DataService,
              private categoryService: CategoryService, private offerService: OfferService,
              private router: Router) {
    this.pageInfo = {
      page: 0,
      size: this.entriesNum,
      totalPages: 1,
      sortBy: Common.SORT_BY_NAME,
      direction: Common.SORT_DIRECTION_ASC
    };

    this.subscriptionPageInfo = this.dataService.adminFoodPageInfo.subscribe(
      pageInfo => {
        if (pageInfo !== undefined && pageInfo.page !== undefined) {
          this.pageInfo = pageInfo;
          this.getFoodList();
        }
      }
    );

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
      }
    );

    this.subscriptionOffers = this.dataService.offers.subscribe(
      offers => {
        if (offers.length === 0) {
          this.offerService.getAllOffers().subscribe(
            ofrs => {
              this.offers = ofrs;
              this.dataService.changeOffers(ofrs);
            }
          );
        } else {
          this.offers = offers;
        }
      }
    );
  }

  ngOnInit(): void {
    this.getFoodList();
  }

  getFoodList(): void {
    if (this.pageInfo === undefined) {
      return;
    }

    this.foodService.getAllFoods(this.pageInfo.page, this.pageInfo.size,
      this.pageInfo.sortBy, this.pageInfo.direction).subscribe(foods => {
        this.foods = foods.list;
        this.pageInfo = foods.pageInfo;
      });
  }

  getDate(strDate?: string): Date {
    if (strDate === undefined) {
      return new Date();
    }

    return new Date(strDate);
  }

  onDeleteFood(id?: number): void {
    if (id === undefined) { return; }

    Swal.fire({
      title: 'Are you sure that you want to remove food?',
      text: '',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No'
    })
      .then((result) => {
        if (result.value) {
          this.foodService.deleteFood(id).subscribe(
            (food: Food) => Swal.fire(`Food ${food.name} removed successfully`, '', 'success'),
            (err: any) => Swal.fire(err.error.message, '', 'error'),
            () => {
              this.pageInfo.page = 0;
              this.dataService.changeAdminFoodPageInfo(this.pageInfo);
              this.router.navigate(['/admin/home/food']);
            }
          );
        }
      });
  }

  onChangeFoodEnabled(food: Food, event: any): void {
    const checked = event.target.checked;

    this.foodService.setFoodEnabled(food, checked).subscribe(
      (f: Food) => Swal.fire(
        `Food ${f.name} ${checked ? 'enabled' : 'disabled'} successfully`, '', 'success'),
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => {
        this.getFoodList();
        this.router.navigate(['/admin/home/food']);
      }
    );
  }

  onSortByName(): void {
    this.pageInfo.page = 0;
    this.pageInfo.sortBy = Common.SORT_BY_NAME;
    this.pageInfo.direction = Common.SORT_DIRECTION_ASC;
    this.dataService.changeAdminFoodPageInfo(this.pageInfo);
  }

  onSortByCategory(): void {
    this.pageInfo.page = 0;
    this.pageInfo.sortBy = Common.SORT_BY_CATEGORY;
    this.pageInfo.direction = Common.SORT_DIRECTION_ASC;
    this.dataService.changeAdminFoodPageInfo(this.pageInfo);
  }

  onSortByPrice(): void {
    this.pageInfo.page = 0;
    this.pageInfo.sortBy = Common.SORT_BY_PRICE;
    this.pageInfo.direction = Common.SORT_DIRECTION_ASC;
    this.dataService.changeAdminFoodPageInfo(this.pageInfo);
  }

  onSortByOffer(): void {
    this.pageInfo.page = 0;
    this.pageInfo.sortBy = Common.SORT_BY_OFFER;
    this.pageInfo.direction = Common.SORT_DIRECTION_ASC;
    this.dataService.changeAdminFoodPageInfo(this.pageInfo);
  }

  onSortByDate(): void {
    this.pageInfo.page = 0;
    this.pageInfo.sortBy = Common.SORT_BY_DATE;
    this.pageInfo.direction = Common.SORT_DIRECTION_ASC;
    this.dataService.changeAdminFoodPageInfo(this.pageInfo);
  }

  onSortByEnabled(): void {
    this.pageInfo.page = 0;
    this.pageInfo.sortBy = Common.SORT_BY_ENABLED;
    this.pageInfo.direction = Common.SORT_DIRECTION_ASC;
    this.dataService.changeAdminFoodPageInfo(this.pageInfo);
  }

  getFirstPageItemStyle(): string {
    return 'page-item ' + (this.pageInfo.page === 0 ? 'disabled' : '');
  }

  getLastPageItemStyle(): string {
    if (this.pageInfo.totalPages === undefined) {
      return 'page-item';
    }

    return 'page-item ' + (this.pageInfo.page === this.pageInfo.totalPages - 1 ? 'disabled' : '');
  }

  getCurrentPageItemStyle(page: number): string {
    return 'page-item ' + (this.pageInfo.page === page - 1 ? 'active' : '');
  }

  getPageNumberRange(): number[] {
    return Array(this.pageInfo.totalPages).fill(0).map((x, i) => i + 1);
  }

  getNumberRange(total: number): number[] {
    return Array(total).fill(0).map((x, i) => i + 1);
  }

  setPage(page: number): void {
    if (page === undefined) {
      return;
    }

    this.pageInfo.page = page;
    this.dataService.changeAdminFoodPageInfo(this.pageInfo);
  }

  getCategoryName(categoryId: number): string {
    const result: Category[] = this.categories.filter(category => category.id === categoryId);

    if (result.length > 0) {
      return result[0].name;
    }

    return '';
  }

  getOfferName(offerId: number | undefined): string {
    if (offerId === undefined || offerId === null) {
      return 'N/A';
    }

    const result: Offer[] = this.offers.filter(offer => offer.id === offerId);

    if (result.length > 0) {
      return result[0].name;
    }

    return '';
  }

  onChangeNumberOfEntries(event: any): void {
    if (event !== undefined &&
      event.target !== undefined &&
      event.target.value !== undefined) {
      this.entriesNum = event.target.value;
      this.pageInfo.size = event.target.value;
      this.pageInfo.page = 0;
      this.dataService.changeAdminFoodPageInfo(this.pageInfo);
    }
  }

  ngOnDestroy(): void {
    this.subscriptionPageInfo.unsubscribe();
    this.subscriptionCategories.unsubscribe();
    this.subscriptionOffers.unsubscribe();
  }

}
