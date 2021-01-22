import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-testee-category',
  templateUrl: './testee-category.component.html',
  styleUrls: ['./testee-category.component.scss']
})
export class TesteeCategoryComponent implements OnInit, OnDestroy {

  private subscriptionCategories: Subscription;

  constructor(private dataService: DataService, private router: Router) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => {
        if (categories.length > 0) {
          this.dataService.changeDefaultCategoryId(categories[0].id);
          this.router.navigate([`/testee/category/${categories[0].id}`]);
        }
      });
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
