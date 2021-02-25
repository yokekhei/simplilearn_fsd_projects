import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ConfigurationService } from './configuration.service';
import { LoginUser } from './../models/login-user';
import { User } from './../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private rootUrl = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.rootUrl = this.configuration.getValue('apiUrl');
  }

  get loginUser(): LoginUser | null {
    if (sessionStorage.loginUser) {
      return JSON.parse(sessionStorage.loginUser);
    }

    return null;
  }

  set loginUser(o: LoginUser | null) {
    sessionStorage.loginUser = JSON.stringify(o);
  }

  isLoggedIn(role: string): boolean {
    if (this.loginUser && (this.loginUser.role === role)) {
      return true;
    }

    return false;
  }

  removeSession(): void {
    sessionStorage.clear();
  }

  login(user: User): Observable<User> | any {
    return this.http.post<User>(`${this.rootUrl}/login`, user);
  }

  register(user: User): Observable<User> | any {
    return this.http.post<User>(`${this.rootUrl}/register`, user);
  }

}
