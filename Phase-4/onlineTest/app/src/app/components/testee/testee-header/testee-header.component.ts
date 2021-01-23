import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { Category } from 'src/app/model/category';
import { CategoryService } from './../../../services/category.service';
import { Common } from 'src/app/core/common';
import { DataService } from 'src/app/services/data.service';
import { LoginUser } from './../../../model/login-user';
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
  defaultCategoryId = 0;
  private subscriptionLoginUser: Subscription;
  private loginUser: LoginUser;

  constructor(private categoryService: CategoryService, private dataService: DataService,
              private userService: UserService, private router: Router) {
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
      });
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(
      categories => {
        this.categories = categories;
        this.dataService.changeCategories(this.categories);

        if (categories.length > 0) {
          this.defaultCategoryId = categories[0].id;
          this.router.navigate([`/testee/category/${this.defaultCategoryId}`]);
        }
      }
    );
  }

  ngOnDestroy(): void {
    this.subscriptionLoginUser.unsubscribe();
  }

}
