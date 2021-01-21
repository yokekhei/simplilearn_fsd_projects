import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FooterComponent } from './components/common/footer/footer.component';
import { TesteeComponent } from './components/testee/testee/testee.component';
import { TesteeHeaderComponent } from './components/testee/testee-header/testee-header.component';
import { TesterComponent } from './components/tester/tester/tester.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    TesteeComponent,
    TesteeHeaderComponent,
    TesterComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
