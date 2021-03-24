import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

import { Category } from './../../../models/category';
import { CategoryService } from './../../../services/category.service';

@Component({
  selector: 'app-admin-category-update',
  templateUrl: './admin-category-update.component.html',
  styleUrls: ['./admin-category-update.component.scss']
})
export class AdminCategoryUpdateComponent implements OnInit {

  category?: Category;
  name = '';
  imageFileName = '';
  private selectedImages?: FileList;

  constructor(private categoryService: CategoryService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.categoryService.getCategoryById(+params.id).subscribe(
        (category: Category) => {
          this.category = category;
          this.name = category.name;
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
    if (this.category === undefined) { return; }

    this.category.name = form.form.value.categoryName;

    if (this.selectedImages !== undefined) {
      return this.updateCategoryWithImage(this.category, this.selectedImages.item(0) as File);
    }

    this.categoryService.updateCategory(this.category).subscribe(
      (category: Category) => this.category = category,
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => this.router.navigate(['/admin/home/category'])
    );
  }

  updateCategoryWithImage(category: Category, image: File): void {
    this.categoryService.updateCategoryWithImage(category, image).subscribe(
      (c: Category) => this.category = c,
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => this.router.navigate(['/admin/home/category'])
    );
  }

}
