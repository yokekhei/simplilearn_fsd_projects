import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminAuthenticationGuard } from './guards/admin-authentication.guard';
import { AdminCategoriesComponent } from './components/admin/admin-categories/admin-categories.component';
import { AdminCategoryCreateComponent } from './components/admin/admin-category-create/admin-category-create.component';
import { AdminCategoryUpdateComponent } from './components/admin/admin-category-update/admin-category-update.component';
import { AdminComponent } from './components/admin/admin/admin.component';
import { AdminFoodCreateComponent } from './components/admin/admin-food-create/admin-food-create.component';
import { AdminFoodUpdateComponent } from './components/admin/admin-food-update/admin-food-update.component';
import { AdminFoodsComponent } from './components/admin/admin-foods/admin-foods.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { AdminLoginComponent } from './components/admin/admin-login/admin-login.component';
import { AdminLogoutComponent } from './components/admin/admin-logout/admin-logout.component';
import { AdminOfferCreateComponent } from './components/admin/admin-offer-create/admin-offer-create.component';
import { AdminOfferUpdateComponent } from './components/admin/admin-offer-update/admin-offer-update.component';
import { AdminOffersComponent } from './components/admin/admin-offers/admin-offers.component';
import { AdminOrderComponent } from './components/admin/admin-order/admin-order.component';
import { AdminOrderReportComponent } from './components/admin/admin-order-report/admin-order-report.component';
import { AdminUserReportComponent } from './components/admin/admin-user-report/admin-user-report.component';
import { CreditComponent } from './components/common/credit/credit.component';
import { UserAuthenticationGuard } from './guards/user-authentication.guard';
import { UserCartComponent } from './components/user/user-cart/user-cart.component';
import { UserCategoryComponent } from './components/user/user-category/user-category.component';
import { UserCheckoutComponent } from './components/user/user-checkout/user-checkout.component';
import { UserFoodComponent } from './components/user/user-food/user-food.component';
import { UserFoodsComponent } from './components/user/user-foods/user-foods.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { UserLoginComponent } from './components/user/user-login/user-login.component';
import { UserLogoutComponent } from './components/user/user-logout/user-logout.component';
import { UserOrderComponent } from './components/user/user-order/user-order.component';
import { UserOrderHistoryComponent } from './components/user/user-order-history/user-order-history.component';
import { UserRegisterComponent } from './components/user/user-register/user-register.component';

const routes: Routes = [
  // User
  {
    path: 'user', component: UserHomeComponent,
    children: [
      { path: 'category', component: UserCategoryComponent },
      { path: 'category/:id', component: UserFoodsComponent },
      { path: 'food/search/:keyword', component: UserFoodsComponent },
      { path: 'food/:id', component: UserFoodComponent },
      { path: 'cart', component: UserCartComponent },
      { path: 'checkout', component: UserCheckoutComponent, canActivate: [ UserAuthenticationGuard ] },
      { path: 'order/history', component: UserOrderHistoryComponent, canActivate: [ UserAuthenticationGuard ] },
      { path: 'order/:id', component: UserOrderComponent, canActivate: [ UserAuthenticationGuard ] },
      { path: 'login', component: UserLoginComponent },
      { path: 'logout', component: UserLogoutComponent },
      { path: 'register', component: UserRegisterComponent },
      { path: '**', redirectTo: '/user/category', pathMatch: 'full' }
    ]
  },

  // Admin
  {
    path: 'admin', component: AdminComponent,
    children: [
      { path: 'login', component: AdminLoginComponent },
      { path: 'logout', component: AdminLogoutComponent },
      { path: 'home', component: AdminHomeComponent, canActivate: [ AdminAuthenticationGuard ],
        children: [
          { path: 'category', component: AdminCategoriesComponent },
          { path: 'category/add', component: AdminCategoryCreateComponent },
          { path: 'category/update/:id', component: AdminCategoryUpdateComponent },
          { path: 'food', component: AdminFoodsComponent },
          { path: 'food/add', component: AdminFoodCreateComponent },
          { path: 'food/update/:id', component: AdminFoodUpdateComponent },
          { path: 'offer', component: AdminOffersComponent },
          { path: 'offer/add', component: AdminOfferCreateComponent },
          { path: 'offer/update/:id', component: AdminOfferUpdateComponent },
          { path: 'report/order', component: AdminOrderReportComponent },
          { path: 'order/:id', component: AdminOrderComponent },
          { path: 'report/user', component: AdminUserReportComponent },
          { path: '**', redirectTo: '/admin/home/category', pathMatch: 'full' }
        ]
      },
      { path: '**', redirectTo: '/admin/login', pathMatch: 'full' }
    ]
  },

  // General
  { path: 'credit', component: CreditComponent },
  { path: '**', redirectTo: '/user/category', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
