import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './guards/auth.guard';
import { CreditComponent } from './components/common/credit/credit.component';
import { TesteeComponent } from './components/testee/testee/testee.component';
import { TesteeCategoryComponent } from './components/testee/testee-category/testee-category.component';
import { TesteeLoginComponent } from './components/testee/testee-login/testee-login.component';
import { TesteeLogoutComponent } from './components/testee/testee-logout/testee-logout.component';
import { TesteeQuizCatalogComponent } from './components/testee/testee-quiz-catalog/testee-quiz-catalog.component';
import { TesteeQuizComponent } from './components/testee/testee-quiz/testee-quiz.component';
import { TesteeQuizResultComponent } from './components/testee/testee-quiz-result/testee-quiz-result.component';
import { TesteeRegisterComponent } from './components/testee/testee-register/testee-register.component';
import { TesterComponent } from './components/tester/tester/tester.component';
import { TesterHomeComponent } from './components/tester/tester-home/tester-home.component';
import { TesterLoginComponent } from './components/tester/tester-login/tester-login.component';
import { TesterLogoutComponent } from './components/tester/tester-logout/tester-logout.component';
import { TesterQuizzesComponent } from './components/tester/tester-quizzes/tester-quizzes.component';
import { TesterQuizCreateComponent } from './components/tester/tester-quiz-create/tester-quiz-create.component';
import { TesterQuizUpdateComponent } from './components/tester/tester-quiz-update/tester-quiz-update.component';
import { TesterRegisterComponent } from './components/tester/tester-register/tester-register.component';

const routes: Routes = [
  // Testee
  { path: 'testee', component: TesteeComponent,
    children: [
      { path: 'category', component: TesteeCategoryComponent },
      { path: 'category/:id', component: TesteeQuizCatalogComponent },
      { path: 'login', component: TesteeLoginComponent },
      { path: 'logout', component: TesteeLogoutComponent },
      { path: 'register', component: TesteeRegisterComponent },
      { path: 'quiz/:id', component: TesteeQuizComponent },
      { path: 'quiz/:id/result', component: TesteeQuizResultComponent },
      { path: '**', redirectTo: '/testee/category', pathMatch: 'full' }
    ]
  },

  // Tester
  { path: 'tester', component: TesterComponent,
    children: [
      { path: 'login', component: TesterLoginComponent },
      { path: 'logout', component: TesterLogoutComponent },
      { path: 'register', component: TesterRegisterComponent },
      { path: 'home', component: TesterHomeComponent, canActivate: [ AuthGuard ],
        children: [
          { path: 'quiz', component: TesterQuizzesComponent },
          { path: 'quiz/add', component: TesterQuizCreateComponent },
          { path: 'quiz/update/:id', component: TesterQuizUpdateComponent },
          { path: '**', redirectTo: '/tester/home/quiz', pathMatch: 'full' }
        ]
      },
      { path: '**', redirectTo: '/tester/login', pathMatch: 'full' }
    ]
  },

  // General
  { path: 'credit', component: CreditComponent },
  { path: '**', redirectTo: '/testee/category', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
