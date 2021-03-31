import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgModule } from '@angular/core';

import { environment } from './../environments/environment';

import { AdminCategoriesComponent } from './components/admin/admin-categories/admin-categories.component';
import { AdminCategoryCreateComponent } from './components/admin/admin-category-create/admin-category-create.component';
import { AdminCategoryUpdateComponent } from './components/admin/admin-category-update/admin-category-update.component';
import { AdminComponent } from './components/admin/admin/admin.component';
import { AdminFoodCreateComponent } from './components/admin/admin-food-create/admin-food-create.component';
import { AdminFoodUpdateComponent } from './components/admin/admin-food-update/admin-food-update.component';
import { AdminFoodsComponent } from './components/admin/admin-foods/admin-foods.component';
import { AdminHeaderComponent } from './components/admin/admin-header/admin-header.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { AdminLoginComponent } from './components/admin/admin-login/admin-login.component';
import { AdminLogoutComponent } from './components/admin/admin-logout/admin-logout.component';
import { AdminOfferCreateComponent } from './components/admin/admin-offer-create/admin-offer-create.component';
import { AdminOfferUpdateComponent } from './components/admin/admin-offer-update/admin-offer-update.component';
import { AdminOffersComponent } from './components/admin/admin-offers/admin-offers.component';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthenticationService } from './services/authentication.service';
import { CartService } from './services/cart.service';
import { CategoryService } from './services/category.service';
import { ConfigurationService, ENVIRONMENT } from './services/configuration.service';
import { CreditComponent } from './components/common/credit/credit.component';
import { DataService } from './services/data.service';
import { FeeService } from './services/fee.service';
import { FoodService } from './services/food.service';
import { FooterComponent } from './components/common/footer/footer.component';
import { OfferService } from './services/offer.service';
import { UserCartComponent } from './components/user/user-cart/user-cart.component';
import { UserCategoryComponent } from './components/user/user-category/user-category.component';
import { UserCheckoutComponent } from './components/user/user-checkout/user-checkout.component';
import { UserFoodComponent } from './components/user/user-food/user-food.component';
import { UserFoodsComponent } from './components/user/user-foods/user-foods.component';
import { UserHeaderComponent } from './components/user/user-header/user-header.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { UserLoginComponent } from './components/user/user-login/user-login.component';
import { UserLogoutComponent } from './components/user/user-logout/user-logout.component';
import { UserRegisterComponent } from './components/user/user-register/user-register.component';

@NgModule({
  declarations: [
    AdminCategoriesComponent,
    AdminCategoryCreateComponent,
    AdminCategoryUpdateComponent,
    AdminComponent,
    AdminFoodCreateComponent,
    AdminFoodUpdateComponent,
    AdminFoodsComponent,
    AdminHeaderComponent,
    AdminHomeComponent,
    AdminLoginComponent,
    AdminLogoutComponent,
    AdminOfferCreateComponent,
    AdminOfferUpdateComponent,
    AdminOffersComponent,
    AppComponent,
    CreditComponent,
    FooterComponent,
    UserCartComponent,
    UserCategoryComponent,
    UserCheckoutComponent,
    UserFoodComponent,
    UserFoodsComponent,
    UserHeaderComponent,
    UserHomeComponent,
    UserLoginComponent,
    UserLogoutComponent,
    UserRegisterComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [
    { provide: ENVIRONMENT, useValue: environment },
    AuthenticationService,
    CartService,
    CategoryService,
    ConfigurationService,
    DataService,
    FeeService,
    FoodService,
    OfferService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
