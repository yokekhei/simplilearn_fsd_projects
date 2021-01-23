import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ConfigurationService } from './configuration.service';
import { LoginUser } from '../model/login-user';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private rootUrl = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.rootUrl = this.configuration.getValue('apiUrl');
  }

  get loginUser(): LoginUser {
    return JSON.parse(sessionStorage.loginUser);
  }

  set loginUser(o: LoginUser) {
    sessionStorage.loginUser = JSON.stringify(o);
  }

  isLoggedIn(): boolean {
    return sessionStorage.loginUser;
  }

  login(user: User): Observable<User> | any {
    return this.http.post<User>(`${this.rootUrl}/login`, user);
  }

  register(user: User): Observable<User> | any {
    return this.http.post<User>(`${this.rootUrl}/register`, user);
  }

}
