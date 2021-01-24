import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './guards/auth.guard';
import { TesteeComponent } from './components/testee/testee/testee.component';
import { TesteeCategoryComponent } from './components/testee/testee-category/testee-category.component';
import { TesteeLoginComponent } from './components/testee/testee-login/testee-login.component';
import { TesteeLogoutComponent } from './components/testee/testee-logout/testee-logout.component';
import { TesteeQuizCatalogComponent } from './components/testee/testee-quiz-catalog/testee-quiz-catalog.component';
import { TesteeRegisterComponent } from './components/testee/testee-register/testee-register.component';
import { TesterComponent } from './components/tester/tester/tester.component';
import { TesterHomeComponent } from './components/tester/tester-home/tester-home.component';
import { TesterLoginComponent } from './components/tester/tester-login/tester-login.component';
import { TesterLogoutComponent } from './components/tester/tester-logout/tester-logout.component';
import { TesterRegisterComponent } from './components/tester/tester-register/tester-register.component';

const routes: Routes = [
  // Testee
  { path: 'testee', component: TesteeComponent,
    children: [
      { path: 'category', component: TesteeCategoryComponent,
        children: [
          { path: ':id', component: TesteeQuizCatalogComponent }
        ]
      },
      { path: 'login', component: TesteeLoginComponent },
      { path: 'logout', component: TesteeLogoutComponent },
      { path: 'register', component: TesteeRegisterComponent }
    ]
  },

  // Tester
  { path: 'tester', component: TesterComponent,
    children: [
      { path: 'login', component: TesterLoginComponent },
      { path: 'logout', component: TesterLogoutComponent },
      { path: 'register', component: TesterRegisterComponent },
      { path: 'home', component: TesterHomeComponent, canActivate: [ AuthGuard ] },
      { path: '**', redirectTo: '/tester/login', pathMatch: 'full' }
    ]
  },

  // General
  { path: '**', redirectTo: '/testee/category', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
