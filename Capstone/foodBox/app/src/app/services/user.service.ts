import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ConfigurationService } from './configuration.service';
import { User } from './../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.url = `${this.configuration.getValue('apiUrl')}/user`;
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.url}`).pipe(
      map(users => {
        return users.map(user => {
          return {
            email: user.email,
            password: user.password,
            username: user.username,
            phone: user.phone,
            role: user.role,
            address: user.address,
            enabled: user.enabled,
            createdDateTime: user.createdDateTime
          };
        });
      })
    );
  }

  getUsersBetweenDate(startDate: string, endDate: string): Observable<User[]> {
    let params = new HttpParams();
    params = params.append('startDate', startDate);
    params = params.append('endDate', endDate);

    return this.http.get<User[]>(`${this.url}`, { params }).pipe(
      map(users => {
        return users.map(user => {
          return {
            email: user.email,
            password: user.password,
            username: user.username,
            phone: user.phone,
            role: user.role,
            address: user.address,
            enabled: user.enabled,
            createdDateTime: user.createdDateTime
          };
        });
      })
    );
  }

  getUserById(email: string): Observable<User> {
    return this.http.get<User>(`${this.url}/${email}`);
  }

}
