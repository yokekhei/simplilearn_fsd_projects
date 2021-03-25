import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import Swal from 'sweetalert2';

import { Category } from './../../../models/category';
import { DataService } from 'src/app/services/data.service';
import { Food } from 'src/app/models/food';
import { FoodService } from './../../../services/food.service';
import { Offer } from './../../../models/offer';

@Component({
  selector: 'app-admin-food-update',
  templateUrl: './admin-food-update.component.html',
  styleUrls: ['./admin-food-update.component.scss']
})
export class AdminFoodUpdateComponent implements OnInit, OnDestroy {

  food?: Food;
  name = '';
  categoryId: number | string = '';
  offerId: number | string = '';
  price = 0.0;
  desc = '';
  imageFileName = '';
  categories: Category[] = [];
  offers: Offer[] = [];
  private selectedImages?: FileList;
  private subscriptionCategories: Subscription;
  private subscriptionOffers: Subscription;

  constructor(private foodService: FoodService,
              private dataService: DataService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories
    );

    this.subscriptionOffers = this.dataService.offers.subscribe(
      offers => this.offers = offers
    );
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.foodService.getFoodById(+params.id).subscribe(
        (food: Food) => {
          this.food = food;
          this.name = food.name;
          this.categoryId = food.categoryId;
          this.offerId = food.offerId || '';
          this.price = food.price;
          this.desc = food.desc;
        },
        (err: any) => Swal.fire(err.error.message, '', 'error')
      );
    });
  }

  onImageSelected(event: any): void {
    this.selectedImages = event.target.files;
    this.imageFileName = event.target.files[0].name;
  }

  submit(form: NgForm): void {
    if (this.food === undefined) { return; }

    this.food.name = form.form.value.foodName;
    this.food.categoryId = form.form.value.foodCategory;
    this.food.price = form.form.value.foodPrice;
    this.food.desc = form.form.value.foodDesc;
    this.food.offerId = form.form.value.foodOffer;

    if (this.selectedImages !== undefined) {
      return this.updateFoodWithImage(this.food, this.selectedImages.item(0) as File);
    }

    this.foodService.updateFood(this.food).subscribe(
      (food: Food) => this.food = food,
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => this.router.navigate(['/admin/home/food'])
    );
  }

  updateFoodWithImage(food: Food, image: File): void {
    this.foodService.updateFoodWithImage(food, image).subscribe(
      (f: Food) => this.food = f,
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => this.router.navigate(['/admin/home/food'])
    );
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
    this.subscriptionOffers.unsubscribe();
  }

}
