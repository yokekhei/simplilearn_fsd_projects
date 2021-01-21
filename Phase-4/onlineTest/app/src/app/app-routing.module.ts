import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TesteeComponent } from './components/testee/testee/testee.component';
import { TesterComponent } from './components/tester/tester/tester.component';

const routes: Routes = [
  // Testee
  { path: 'testee', component: TesteeComponent },

  // Tester
  { path: 'tester', component: TesterComponent },

  // General
  { path: '**', redirectTo: '/testee', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
