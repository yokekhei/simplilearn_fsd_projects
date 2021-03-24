import { DataService } from './../../../services/data.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

import { Category } from './../../../models/category';
import { CategoryService } from './../../../services/category.service';

@Component({
  selector: 'app-admin-categories',
  templateUrl: './admin-categories.component.html',
  styleUrls: ['./admin-categories.component.scss']
})
export class AdminCategoriesComponent implements OnInit {

  categories: Category[] = [];

  constructor(private categoryService: CategoryService,
              private dataService: DataService,
              private router: Router) { }

  ngOnInit(): void {
    this.getCategoryList();
  }

  getCategoryList(): void {
    this.categoryService.getAllCategories().subscribe(
      categories => {
        this.categories = categories;
        this.dataService.changeCategories(categories);
      }
    );
  }

  getDate(strDate?: string): Date {
    if (strDate === undefined) {
      return new Date();
    }

    return new Date(strDate);
  }

  onDeleteCategory(id?: number): void {
    if (id === undefined) { return; }

    Swal.fire({
      title: 'Are you sure that you want to remove category?',
      text: '',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No'
    })
      .then((result) => {
        if (result.value) {
          this.categoryService.deleteCategory(id).subscribe(
            (category: Category) => Swal.fire(`Category ${category.name} removed successfully`,
              '', 'success'),
            (err: any) => Swal.fire(err.error.message, '', 'error'),
            () => {
              this.getCategoryList();
              this.router.navigate(['/admin/home/category']);
            }
          );
        }
      });
  }

  onChangeCategoryEnabled(category: Category, event: any): void {
    const checked = event.target.checked;

    this.categoryService.setCategoryEnabled(category, checked).subscribe(
      (c: Category) => Swal.fire(`Category ${c.name} ${checked ? 'enabled' : 'disabled'} successfully`,
        '', 'success'),
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => {
        this.getCategoryList();
        this.router.navigate(['/admin/home/category']);
      }
    );
  }

}
