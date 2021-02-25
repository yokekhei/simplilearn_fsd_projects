import { BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';

import { LoginUser } from './../models/login-user';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private loginUserSource: BehaviorSubject<LoginUser>;
  loginUser: Observable<LoginUser>;

  constructor() {
    this.loginUserSource = new BehaviorSubject({} as LoginUser);
    this.loginUser = this.loginUserSource.asObservable();
  }

  changeLoginUser(loginUser: LoginUser): void {
    this.loginUserSource.next(loginUser);
  }

}
