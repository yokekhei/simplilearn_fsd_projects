import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TesteeComponent } from './components/testee/testee/testee.component';
import { TesteeCategoryComponent } from './components/testee/testee-category/testee-category.component';
import { TesteeLoginComponent } from './components/testee/testee-login/testee-login.component';
import { TesteeLogoutComponent } from './components/testee/testee-logout/testee-logout.component';
import { TesteeQuizCatalogComponent } from './components/testee/testee-quiz-catalog/testee-quiz-catalog.component';
import { TesteeRegisterComponent } from './components/testee/testee-register/testee-register.component';
import { TesterComponent } from './components/tester/tester/tester.component';

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
  { path: 'tester', component: TesterComponent },

  // General
  { path: '**', redirectTo: '/testee/category', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
