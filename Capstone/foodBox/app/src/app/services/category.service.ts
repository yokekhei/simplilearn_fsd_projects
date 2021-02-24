import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Category } from './../models/category';
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
    let params = new HttpParams();
    params = params.append('enabled', 'true');

    return this.http.get<Category[]>(`${this.rootUrl}/category`, { params }).pipe(
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

  getCategoryImageUrl(id: number): string {
    return `${this.rootUrl}/category/${id}/image`;
  }

}
