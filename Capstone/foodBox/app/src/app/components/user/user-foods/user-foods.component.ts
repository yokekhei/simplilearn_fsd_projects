import { ActivatedRoute } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { Category } from './../../../models/category';
import { CategoryService } from './../../../services/category.service';
import { Common } from './../../../core/common';
import { DataService } from './../../../services/data.service';
import { Food } from 'src/app/models/food';
import { Foods } from './../../../models/foods';
import { FoodService } from './../../../services/food.service';
import { PageInfo } from './../../../models/page-info';
import { Offer } from './../../../models/offer';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-user-foods',
  templateUrl: './user-foods.component.html',
  styleUrls: ['./user-foods.component.scss']
})
export class UserFoodsComponent implements OnInit, OnDestroy {

  private COL_NUM = 4;
  private keyword = '';
  private subscriptionPageInfo: Subscription;
  private subscriptionCategories: Subscription;
  private subscriptionOffers: Subscription;
  foodList: { foods: Food[] }[] = [];
  pageInfo: PageInfo;
  offers: Offer[] = [];
  categories: Category[] = [];
  entriesNum = this.COL_NUM;
  currentCategoryId = 0;
  currentOfferId = 0;

  constructor(private foodService: FoodService, private dataService: DataService,
              private categoryService: CategoryService, private offerService: OfferService,
              private activatedRoute: ActivatedRoute) {
    this.pageInfo = {
      page: 0,
      size: this.entriesNum,
      totalPages: 1,
      sortBy: Common.SORT_BY_NAME,
      direction: Common.SORT_DIRECTION_ASC
    };

    this.subscriptionPageInfo = this.dataService.userFoodPageInfo.subscribe(
      pageInfo => {
        if (pageInfo !== undefined && pageInfo.page !== undefined) {
          this.pageInfo = pageInfo;

          if (this.keyword.length > 0) {
            this.refreshFoodListByKeyword(this.keyword);
          } else {
            this.refreshFoodList();
          }
        }
      }
    );

    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => {
        if (categories.length === 0) {
          this.categoryService.getCategories().subscribe(
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
    this.activatedRoute.params.subscribe(params => {
      this.initPageInfo();

      if (params.id !== undefined) {
        this.currentCategoryId = +params.id;
        this.currentOfferId = Common.ALL_VALUES;
        this.dataService.changeUserFoodPageInfo(this.pageInfo);
      } else if (params.keyword !== undefined) {
        this.keyword = params.keyword;
        this.currentCategoryId = Common.ALL_VALUES;
        this.currentOfferId = Common.ALL_VALUES;
        this.dataService.changeUserFoodPageInfo(this.pageInfo);
      }
    });
  }

  initPageInfo(): void {
    this.pageInfo = {
      page: 0,
      size: this.entriesNum,
      totalPages: 1,
      sortBy: Common.SORT_BY_NAME,
      direction: Common.SORT_DIRECTION_ASC
    };
  }

  getFoodImageUrl(foodId: number | undefined): string {
    if (foodId === undefined) { return ''; }

    return this.foodService.getFoodImageUrl(foodId);
  }

  refreshFoodList(): void {
    if (this.currentCategoryId === Common.ALL_VALUES &&
      this.currentOfferId === Common.ALL_VALUES) {
      this.refreshFoodListByAll();
    } else if (this.currentCategoryId > 0 && this.currentCategoryId < Common.ALL_VALUES &&
      this.currentOfferId > 0 && this.currentOfferId < Common.ALL_VALUES) {
      this.refreshFoodListByCategoryAndOffer(
        this.currentCategoryId, this.currentOfferId);
    } else if (this.currentCategoryId > 0 && this.currentCategoryId < Common.ALL_VALUES) {
      this.refreshFoodListByCategory(this.currentCategoryId);
    } else if (this.currentOfferId > 0 && this.currentOfferId < Common.ALL_VALUES) {
      this.refreshFoodListByOffer(this.currentOfferId);
    }
  }

  refreshFoodListByAll(): void {
    if (this.pageInfo === undefined) {
      return;
    }

    this.foodService.getFoods(this.pageInfo.page,
      this.pageInfo.size, this.pageInfo.sortBy, this.pageInfo.direction).subscribe(
        foodsInfo => this.display(foodsInfo));
  }

  refreshFoodListByCategory(categoryId: number): void {
    if (this.pageInfo === undefined) {
      return;
    }

    this.foodService.getFoodsByCategory(categoryId, this.pageInfo.page,
      this.pageInfo.size, this.pageInfo.sortBy, this.pageInfo.direction).subscribe(
        foodsInfo => this.display(foodsInfo));
  }

  refreshFoodListByOffer(offerId: number): void {
    if (this.pageInfo === undefined) {
      return;
    }

    this.foodService.getFoodsByOffer(offerId, this.pageInfo.page,
      this.pageInfo.size, this.pageInfo.sortBy, this.pageInfo.direction).subscribe(
        foodsInfo => this.display(foodsInfo));
  }

  refreshFoodListByCategoryAndOffer(categoryId: number, offerId: number): void {
    if (this.pageInfo === undefined) {
      return;
    }

    this.foodService.getFoodsByCategoryAndOffer(categoryId, offerId, this.pageInfo.page,
      this.pageInfo.size, this.pageInfo.sortBy, this.pageInfo.direction).subscribe(
        foodsInfo => this.display(foodsInfo));
  }

  refreshFoodListByKeyword(keyword: string): void {
    if (this.pageInfo === undefined) {
      return;
    }

    this.foodService.getFoodsByKeyword(keyword, this.pageInfo.page,
      this.pageInfo.size, this.pageInfo.sortBy, this.pageInfo.direction).subscribe(
        foodsInfo => this.display(foodsInfo));
  }

  display(foodsInfo: Foods): void {
    const foods: Food[] = foodsInfo.list;
    this.pageInfo = foodsInfo.pageInfo;

    this.foodList = [];

    if (foods.length > 0) {
      const rowNumber = (foods.length / this.COL_NUM) + (foods.length % this.COL_NUM) - 1;
      let index = 0;
      let count = foods.length;

      for (let row = 0; row <= rowNumber; row++) {
        const rowFoods: Food[] = [];

        for (let col = 0; col < this.COL_NUM && count > 0; col++) {
          const food: Food = foods[index++];
          rowFoods.push(food);
          count--;
        }

        this.foodList.push({ foods: rowFoods });
      }
    }
  }

  getNumberRange(total: number): number[] {
    return Array(total).fill(0).map((x, i) => i + 1);
  }

  onChangeNumberOfEntries(event: any): void {
    if (event !== undefined &&
      event.target !== undefined &&
      event.target.value !== undefined &&
      event.target.value !== '') {
      this.entriesNum = event.target.value;
      this.pageInfo.size = event.target.value;
      this.pageInfo.page = 0;
      this.dataService.changeUserFoodPageInfo(this.pageInfo);
    }
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

  setPage(page: number): void {
    if (page === undefined) {
      return;
    }

    this.pageInfo.page = page;
    this.dataService.changeUserFoodPageInfo(this.pageInfo);
  }

  onSortByName(): void {
    this.pageInfo.page = 0;
    this.pageInfo.sortBy = Common.SORT_BY_NAME;
    this.pageInfo.direction = Common.SORT_DIRECTION_ASC;
    this.dataService.changeUserFoodPageInfo(this.pageInfo);
  }

  onSortByLowestPrice(): void {
    this.pageInfo.page = 0;
    this.pageInfo.sortBy = Common.SORT_BY_PRICE;
    this.pageInfo.direction = Common.SORT_DIRECTION_ASC;
    this.dataService.changeUserFoodPageInfo(this.pageInfo);
  }

  onSortByHighestPrice(): void {
    this.pageInfo.page = 0;
    this.pageInfo.sortBy = Common.SORT_BY_PRICE;
    this.pageInfo.direction = Common.SORT_DIRECTION_DESC;
    this.dataService.changeUserFoodPageInfo(this.pageInfo);
  }

  onChangeSort(event: any): void {
    if (event !== undefined &&
      event.target !== undefined &&
      event.target.value !== undefined &&
      event.target.value !== '') {
      switch (event.target.value) {
        case 'name':
          this.onSortByName();
          break;

        case 'lowestPrice':
          this.onSortByLowestPrice();
          break;

        case 'highestPrice':
          this.onSortByHighestPrice();
          break;

        default:
          console.log('Invalid sorting selection');
      }
    }
  }

  onChangeOffer(event: any): void {
    if (event !== undefined &&
      event.target !== undefined &&
      event.target.value !== undefined) {
      if (event.target.value === '9999') {
        this.currentOfferId = Common.ALL_VALUES;
      } else {
        this.currentOfferId = event.target.value;
      }

      this.keyword = '';
      this.pageInfo.page = 0;
      this.dataService.changeUserFoodPageInfo(this.pageInfo);
    }
  }

  onChangeCategory(event: any): void {
    if (event !== undefined &&
      event.target !== undefined &&
      event.target.value !== undefined) {
      if (event.target.value === '9999') {
        this.currentCategoryId = Common.ALL_VALUES;
      } else {
        this.currentCategoryId = event.target.value;
      }

      this.keyword = '';
      this.pageInfo.page = 0;
      this.dataService.changeUserFoodPageInfo(this.pageInfo);
    }
  }

  ngOnDestroy(): void {
    this.subscriptionPageInfo.unsubscribe();
    this.subscriptionCategories.unsubscribe();
    this.subscriptionOffers.unsubscribe();
  }

}
