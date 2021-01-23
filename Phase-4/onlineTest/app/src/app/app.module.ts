import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgModule } from '@angular/core';

import { environment } from './../environments/environment';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CategoryService } from './services/category.service';
import { DataService } from './services/data.service';
import { ENVIRONMENT, ConfigurationService } from './services/configuration.service';
import { FooterComponent } from './components/common/footer/footer.component';
import { TesteeCategoryComponent } from './components/testee/testee-category/testee-category.component';
import { TesteeComponent } from './components/testee/testee/testee.component';
import { TesteeHeaderComponent } from './components/testee/testee-header/testee-header.component';
import { TesteeLoginComponent } from './components/testee/testee-login/testee-login.component';
import { TesteeLogoutComponent } from './components/testee/testee-logout/testee-logout.component';
import { TesteeQuizCatalogComponent } from './components/testee/testee-quiz-catalog/testee-quiz-catalog.component';
import { TesteeRegisterComponent } from './components/testee/testee-register/testee-register.component';
import { TesterComponent } from './components/tester/tester/tester.component';
import { TesterHeaderComponent } from './components/tester/tester-header/tester-header.component';
import { TesterHomeComponent } from './components/tester/tester-home/tester-home.component';
import { TesterLoginComponent } from './components/tester/tester-login/tester-login.component';
import { UserService } from './services/user.service';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    TesteeComponent,
    TesteeCategoryComponent,
    TesteeHeaderComponent,
    TesteeLoginComponent,
    TesteeLogoutComponent,
    TesteeQuizCatalogComponent,
    TesteeRegisterComponent,
    TesterComponent,
    TesterHeaderComponent,
    TesterHomeComponent,
    TesterLoginComponent
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
    CategoryService,
    ConfigurationService,
    DataService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
