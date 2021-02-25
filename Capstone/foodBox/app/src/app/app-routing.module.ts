import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminComponent } from './components/admin/admin/admin.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { AdminLoginComponent } from './components/admin/admin-login/admin-login.component';
import { AdminLogoutComponent } from './components/admin/admin-logout/admin-logout.component';
import { CreditComponent } from './components/common/credit/credit.component';
import { UserCategoryComponent } from './components/user/user-category/user-category.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { UserLoginComponent } from './components/user/user-login/user-login.component';
import { UserLogoutComponent } from './components/user/user-logout/user-logout.component';
import { UserRegisterComponent } from './components/user/user-register/user-register.component';

const routes: Routes = [
  // User
  {
    path: 'user', component: UserHomeComponent,
    children: [
      { path: 'category', component: UserCategoryComponent },
      { path: 'login', component: UserLoginComponent },
      { path: 'logout', component: UserLogoutComponent },
      { path: 'register', component: UserRegisterComponent },
      { path: '**', redirectTo: '/user/category', pathMatch: 'full' }
    ]
  },

  // Admin
  {
    path: 'admin', component: AdminComponent,
    children: [
      { path: 'login', component: AdminLoginComponent },
      { path: 'logout', component: AdminLogoutComponent },
      { path: 'home', component: AdminHomeComponent,
        children: [

        ]
      },
      { path: '**', redirectTo: '/admin/login', pathMatch: 'full' }
    ]
  },

  // General
  { path: 'credit', component: CreditComponent },
  { path: '**', redirectTo: '/user/category', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
