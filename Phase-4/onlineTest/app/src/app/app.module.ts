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
import { FooterComponent } from './components/common/footer/footer.component';
import { ENVIRONMENT, ConfigurationService } from './services/configuration.service';
import { TesteeComponent } from './components/testee/testee/testee.component';
import { TesteeHeaderComponent } from './components/testee/testee-header/testee-header.component';
import { TesteeCategoryComponent } from './components/testee/testee-category/testee-category.component';
import { TesteeQuizCatalogComponent } from './components/testee/testee-quiz-catalog/testee-quiz-catalog.component';
import { TesterComponent } from './components/tester/tester/tester.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    TesteeComponent,
    TesteeHeaderComponent,
    TesteeCategoryComponent,
    TesteeQuizCatalogComponent,
    TesterComponent
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
    DataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
