import { Component, OnInit } from '@angular/core';

import { Category } from './../../../models/category';
import { CategoryService } from './../../../services/category.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-user-category',
  templateUrl: './user-category.component.html',
  styleUrls: ['./user-category.component.scss']
})
export class UserCategoryComponent implements OnInit {

  categoryList: { categories: Category[] }[] = [];
  private COL_NUM = 4;

  constructor(private categoryService: CategoryService, private dataService: DataService) { }

  ngOnInit(): void {
    this.refreshCategoryList();
  }

  getCategoryImageUrl(categoryId: number | undefined): string {
    if (categoryId === undefined) { return ''; }

    return this.categoryService.getCategoryImageUrl(categoryId);
  }

  refreshCategoryList(): void {
    this.categoryService.getCategories().subscribe(categories => {
      this.categoryList = [];

      if (categories.length > 0) {
        this.dataService.changeCategories(categories);

        const rowNumber = (categories.length / this.COL_NUM) + (categories.length % this.COL_NUM) - 1;
        let index = 0;
        let count = categories.length;

        for (let row = 0; row <= rowNumber; row++) {
          const rowCategories: Category[] = [];

          for (let col = 0; col < this.COL_NUM && count > 0; col++) {
            const category: Category = categories[index++];
            rowCategories.push(category);
            count--;
          }

          this.categoryList.push({ categories: rowCategories });
        }
      }
    });
  }

}
