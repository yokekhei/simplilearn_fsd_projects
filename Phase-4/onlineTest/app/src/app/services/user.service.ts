import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ConfigurationService } from './configuration.service';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private rootUrl = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.rootUrl = this.configuration.getValue('apiUrl');
  }

  login(user: User): Observable<User> | any {
    return this.http.post<User>(`${this.rootUrl}/login`, user);
  }
}
