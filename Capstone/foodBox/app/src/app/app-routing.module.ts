import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CreditComponent } from './components/common/credit/credit.component';
import { UserCategoryComponent } from './components/user/user-category/user-category.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';

const routes: Routes = [
  // User
  {
    path: 'user', component: UserHomeComponent,
    children: [
      { path: 'category', component: UserCategoryComponent },
      { path: '**', redirectTo: '/user/category', pathMatch: 'full' }
    ]
  },

  // Admin

  // General
  { path: 'credit', component: CreditComponent },
  { path: '**', redirectTo: '/user/category', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
