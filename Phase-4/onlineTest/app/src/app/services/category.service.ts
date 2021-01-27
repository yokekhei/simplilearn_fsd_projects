import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Category } from '../models/category';
import { ConfigurationService } from './configuration.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private rootUrl = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.rootUrl = this.configuration.getValue('apiUrl');
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.rootUrl}/category`).pipe(
      map(categories => {
        return categories.map(category => {
          return {
            id: category.id,
            name: category.name
          };
        });
      })
    );
  }

}
