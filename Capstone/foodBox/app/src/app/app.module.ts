import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
    MDBBootstrapModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
