import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-testee-category',
  templateUrl: './testee-category.component.html',
  styleUrls: ['./testee-category.component.scss']
})
export class TesteeCategoryComponent implements OnInit, OnDestroy {

  categories: Category[] = [];
  private categoryImageUrls: { name: string, url: string }[] = [];
  private subscriptionCategories: Subscription;

  constructor(private dataService: DataService, private categoryService: CategoryService) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories);

    this.setCategoryImageUrl();
  }

  ngOnInit(): void {
    if (this.categories.length > 0) { return; }

    this.categoryService.getCategories().subscribe(
      categories => {
        this.categories = categories;
        this.dataService.changeCategories(this.categories);
      }
    );
  }

  setCategoryImageUrl(): void {
    this.categoryImageUrls.push({
      name: 'Mathematics',
      url: '../../../../assets/images/mathematics.jpg'});
    this.categoryImageUrls.push({
      name: 'English and Language Arts',
      url: '../../../../assets/images/english.jpg'});
    this.categoryImageUrls.push({
      name: 'Computer Science and Skills',
      url: '../../../../assets/images/computer-science.jpg'});
    this.categoryImageUrls.push({
      name: 'Social Studies',
      url: '../../../../assets/images/social-studies.png'});
  }

  getCategoryImageUrl(categoryName: string): string {
    const categories: Category[] = this.categories.filter(c => c.name === categoryName);

    if (categories.length > 0) {
      const urls = this.categoryImageUrls.filter(i => i.name === categories[0].name);

      if (urls.length > 0) { return urls[0].url; }
    }

    return '';
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
