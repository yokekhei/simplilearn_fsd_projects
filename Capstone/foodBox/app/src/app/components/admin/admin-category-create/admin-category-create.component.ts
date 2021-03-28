import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

import { Category } from './../../../models/category';
import { CategoryService } from './../../../services/category.service';

@Component({
  selector: 'app-admin-category-create',
  templateUrl: './admin-category-create.component.html',
  styleUrls: ['./admin-category-create.component.scss']
})
export class AdminCategoryCreateComponent implements OnInit {

  category?: Category;
  imageFileName = '';
  private selectedImages?: FileList;

  constructor(private categoryService: CategoryService, private router: Router) { }

  ngOnInit(): void {
  }

  onImageSelected(event: any): void {
    this.selectedImages = event.target.files;
    this.imageFileName = event.target.files[0].name;
  }

  submit(form: NgForm): void {
    this.category = {
      name: form.form.value.categoryName,
      enabled: true
    };

    if (this.selectedImages !== undefined) {
      return this.addCategoryWithImage(this.category, this.selectedImages.item(0) as File);
    }

    this.categoryService.createCategory(this.category).subscribe(
      (category: Category) => this.category = category,
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => this.router.navigate(['/admin/home/category'])
    );
  }

  addCategoryWithImage(category: Category, image: File): void {
    this.categoryService.createCategoryWithImage(category, image).subscribe(
      (c: Category) => this.category = c,
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => this.router.navigate(['/admin/home/category'])
    );
  }

}
