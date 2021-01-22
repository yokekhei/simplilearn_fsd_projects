import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { Category } from 'src/app/model/category';
import { CategoryService } from './../../../services/category.service';
import { Common } from 'src/app/core/common';
import { DataService } from 'src/app/services/data.service';
import { LoginUser } from './../../../model/login-user';

@Component({
  selector: 'app-testee-header',
  templateUrl: './testee-header.component.html',
  styleUrls: ['./testee-header.component.scss']
})
export class TesteeHeaderComponent implements OnInit, OnDestroy {

  userName: string;
  isGuest: boolean;
  categories: Category[] = [];
  defaultCategoryId = 0;
  private subscriptionDefaultCategoryId: Subscription;
  private subscriptionLoginUser: Subscription;
  private loginUser: LoginUser;

  constructor(private categoryService: CategoryService, private dataService: DataService) {
    if (sessionStorage.loginUser) {
      this.loginUser = JSON.parse(sessionStorage.loginUser);
      this.isGuest = false;
      this.userName = this.loginUser.username;
    } else {
      this.loginUser = { email: '', username: Common.GUEST_NAME, role: Common.ROLE_TESTEE };
      this.isGuest = true;
      this.userName = Common.GUEST_NAME;
    }

    this.subscriptionDefaultCategoryId = this.dataService.defaultCategoryId.subscribe(
      defaultCategoryId => this.defaultCategoryId = defaultCategoryId);
    this.subscriptionLoginUser = this.dataService.loginUser.subscribe(
      loginUser => {
        if (loginUser.username && loginUser.username !== this.userName) {
          this.userName = loginUser.username;
          this.isGuest = false;
        }
      });
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(
      categories => {
        this.categories = categories;
        this.dataService.changeCategories(this.categories);
      }
    );
  }

  ngOnDestroy(): void {
    this.subscriptionDefaultCategoryId.unsubscribe();
    this.subscriptionLoginUser.unsubscribe();
  }

}
