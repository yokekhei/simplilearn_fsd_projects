import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgModule } from '@angular/core';

import { environment } from './../environments/environment';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthenticationService } from './services/authentication.service';
import { CategoryService } from './services/category.service';
import { ConfigurationService, ENVIRONMENT } from './services/configuration.service';
import { CreditComponent } from './components/common/credit/credit.component';
import { DataService } from './services/data.service';
import { FooterComponent } from './components/common/footer/footer.component';
import { UserCategoryComponent } from './components/user/user-category/user-category.component';
import { UserHeaderComponent } from './components/user/user-header/user-header.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { UserLoginComponent } from './components/user/user-login/user-login.component';
import { UserLogoutComponent } from './components/user/user-logout/user-logout.component';
import { UserRegisterComponent } from './components/user/user-register/user-register.component';

@NgModule({
  declarations: [
    AppComponent,
    CreditComponent,
    FooterComponent,
    UserCategoryComponent,
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
    CategoryService,
    ConfigurationService,
    DataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
