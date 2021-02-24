import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgModule } from '@angular/core';

import { environment } from './../environments/environment';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CategoryService } from './services/category.service';
import { ConfigurationService, ENVIRONMENT } from './services/configuration.service';
import { CreditComponent } from './components/common/credit/credit.component';
import { FooterComponent } from './components/common/footer/footer.component';
import { UserCategoryComponent } from './components/user/user-category/user-category.component';
import { UserHeaderComponent } from './components/user/user-header/user-header.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';

@NgModule({
  declarations: [
    AppComponent,
    CreditComponent,
    FooterComponent,
    UserCategoryComponent,
    UserHeaderComponent,
    UserHomeComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [
    { provide: ENVIRONMENT, useValue: environment },
    CategoryService,
    ConfigurationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
