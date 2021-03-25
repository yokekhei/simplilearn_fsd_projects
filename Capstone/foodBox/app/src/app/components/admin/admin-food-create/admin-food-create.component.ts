import { Router } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import Swal from 'sweetalert2';

import { Category } from './../../../models/category';
import { DataService } from './../../../services/data.service';
import { Food } from 'src/app/models/food';
import { FoodService } from './../../../services/food.service';
import { Offer } from './../../../models/offer';

@Component({
  selector: 'app-admin-food-create',
  templateUrl: './admin-food-create.component.html',
  styleUrls: ['./admin-food-create.component.scss']
})
export class AdminFoodCreateComponent implements OnInit, OnDestroy {

  food?: Food;
  categories: Category[] = [];
  offers: Offer[] = [];
  imageFileName = '';
  private selectedImages?: FileList;
  private subscriptionCategories: Subscription;
  private subscriptionOffers: Subscription;

  constructor(private foodService: FoodService, private dataService: DataService,
              private router: Router) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories
    );

    this.subscriptionOffers = this.dataService.offers.subscribe(
      offers => this.offers = offers
    );
  }

  ngOnInit(): void {
  }

  onImageSelected(event: any): void {
    this.selectedImages = event.target.files;
    this.imageFileName = event.target.files[0].name;
  }

  submit(form: NgForm): void {
    this.food = {
      name: form.form.value.foodName,
      categoryId: form.form.value.foodCategory,
      price: form.form.value.foodPrice,
      desc: form.form.value.foodDesc,
      offerId: form.form.value.foodOffer,
      enabled: true
    };

    if (this.selectedImages !== undefined) {
      return this.addFoodWithImage(this.food, this.selectedImages.item(0) as File);
    }

    this.foodService.createFood(this.food).subscribe(
      (food: Food) => this.food = food,
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => this.router.navigate(['/admin/home/food'])
    );
  }

  addFoodWithImage(food: Food, image: File): void {
    this.foodService.createFoodWithImage(food, image).subscribe(
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
