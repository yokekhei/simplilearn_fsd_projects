import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TesteeComponent } from './components/testee/testee/testee.component';
import { TesteeCategoryComponent } from './components/testee/testee-category/testee-category.component';
import { TesteeLoginComponent } from './components/testee/testee-login/testee-login.component';
import { TesteeQuizCatalogComponent } from './components/testee/testee-quiz-catalog/testee-quiz-catalog.component';
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
      { path: 'login', component: TesteeLoginComponent }
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
