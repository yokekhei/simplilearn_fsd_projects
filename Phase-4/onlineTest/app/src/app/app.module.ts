import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgModule } from '@angular/core';

import { environment } from './../environments/environment';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CategoryService } from './services/category.service';
import { CreditComponent } from './components/common/credit/credit.component';
import { DataService } from './services/data.service';
import { ENVIRONMENT, ConfigurationService } from './services/configuration.service';
import { FooterComponent } from './components/common/footer/footer.component';
import { QuizService } from './services/quiz.service';
import { TesteeCategoryComponent } from './components/testee/testee-category/testee-category.component';
import { TesteeComponent } from './components/testee/testee/testee.component';
import { TesteeHeaderComponent } from './components/testee/testee-header/testee-header.component';
import { TesteeLoginComponent } from './components/testee/testee-login/testee-login.component';
import { TesteeLogoutComponent } from './components/testee/testee-logout/testee-logout.component';
import { TesteeQuizCatalogComponent } from './components/testee/testee-quiz-catalog/testee-quiz-catalog.component';
import { TesteeQuizComponent } from './components/testee/testee-quiz/testee-quiz.component';
import { TesteeQuizResultComponent } from './components/testee/testee-quiz-result/testee-quiz-result.component';
import { TesteeQuizReviewComponent } from './components/testee/testee-quiz-review/testee-quiz-review.component';
import { TesteeRegisterComponent } from './components/testee/testee-register/testee-register.component';
import { TesterComponent } from './components/tester/tester/tester.component';
import { TesterHeaderComponent } from './components/tester/tester-header/tester-header.component';
import { TesterHomeComponent } from './components/tester/tester-home/tester-home.component';
import { TesterLoginComponent } from './components/tester/tester-login/tester-login.component';
import { TesterLogoutComponent } from './components/tester/tester-logout/tester-logout.component';
import { TesterQuizzesComponent } from './components/tester/tester-quizzes/tester-quizzes.component';
import { TesterQuizCreateComponent } from './components/tester/tester-quiz-create/tester-quiz-create.component';
import { TesterQuizUpdateComponent } from './components/tester/tester-quiz-update/tester-quiz-update.component';
import { TesterRegisterComponent } from './components/tester/tester-register/tester-register.component';
import { UserService } from './services/user.service';

@NgModule({
  declarations: [
    AppComponent,
    CreditComponent,
    FooterComponent,
    TesteeComponent,
    TesteeCategoryComponent,
    TesteeHeaderComponent,
    TesteeLoginComponent,
    TesteeLogoutComponent,
    TesteeQuizCatalogComponent,
    TesteeQuizComponent,
    TesteeQuizResultComponent,
    TesteeQuizReviewComponent,
    TesteeRegisterComponent,
    TesterComponent,
    TesterHeaderComponent,
    TesterHomeComponent,
    TesterLoginComponent,
    TesterLogoutComponent,
    TesterQuizzesComponent,
    TesterQuizCreateComponent,
    TesterQuizUpdateComponent,
    TesterRegisterComponent
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
    QuizService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
