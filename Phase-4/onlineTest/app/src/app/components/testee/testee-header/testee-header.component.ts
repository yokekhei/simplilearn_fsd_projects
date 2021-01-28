import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { Category } from 'src/app/models/category';
import { CategoryService } from './../../../services/category.service';
import { Common } from 'src/app/core/common';
import { DataService } from 'src/app/services/data.service';
import { LoginUser } from '../../../models/login-user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-testee-header',
  templateUrl: './testee-header.component.html',
  styleUrls: ['./testee-header.component.scss']
})
export class TesteeHeaderComponent implements OnInit, OnDestroy {

  userName: string;
  isGuest: boolean;
  categories: Category[] = [];
  private loginUser: LoginUser;
  private subscriptionLoginUser: Subscription;
  private subscriptionCategories: Subscription;

  constructor(private categoryService: CategoryService, private dataService: DataService,
              private userService: UserService) {
    if (this.userService.isLoggedIn()) {
      this.loginUser = this.userService.loginUser ||
        { email: '', username: '', role: Common.ROLE_TESTEE };
      this.isGuest = false;
    } else {
      this.loginUser = { email: '', username: Common.GUEST_NAME, role: Common.ROLE_TESTEE };
      this.isGuest = true;
    }

    this.userName = this.loginUser.username;

    this.subscriptionLoginUser = this.dataService.loginUser.subscribe(
      loginUser => {
        if (loginUser.username && loginUser.username !== this.userName) {
          this.userName = loginUser.username;
          this.isGuest = this.userService.isLoggedIn() ? false : true;
        }
      }
    );

    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories);
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
    this.subscriptionLoginUser.unsubscribe();
    this.subscriptionCategories.unsubscribe();
  }

}
