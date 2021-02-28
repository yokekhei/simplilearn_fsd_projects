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

  private url = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.url = `${this.configuration.getValue('apiUrl')}/category`;
  }

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.url}`).pipe(
      map(categories => {
        return categories.map(category => {
          return {
            id: category.id,
            name: category.name,
            createdDateTime: category.createdDateTime,
            enabled: category.enabled
          };
        });
      })
    );
  }

  getCategories(): Observable<Category[]> {
    let params = new HttpParams();
    params = params.append('enabled', 'true');

    return this.http.get<Category[]>(`${this.url}`, { params }).pipe(
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
    return `${this.url}/${id}/image`;
  }

  getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.url}/${id}`);
  }

  createCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(this.url, category);
  }

  createCategoryWithImage(category: Category, image: File | null): Observable<Category> {
    const formData: FormData = new FormData();
    formData.append('category', new Blob([JSON.stringify(category)], { type: 'application/json' }));
    formData.append('image', image || '');

    return this.http.post<Category>(`${this.url}/image`, formData);
  }

  updateCategory(category: Category): Observable<Category> {
    return this.http.put<Category>(this.url, category);
  }

  updateCategoryWithImage(category: Category, image: File | null): Observable<Category> {
    const formData: FormData = new FormData();
    formData.append('category', new Blob([JSON.stringify(category)], { type: 'application/json' }));
    formData.append('image', image || '');

    return this.http.put<Category>(`${this.url}/image`, formData);
  }

  deleteCategory(id: number): Observable<Category> {
    return this.http.delete<Category>(`${this.url}/${id}`);
  }

  setCategoryEnabled(category: Category, enabledStatus: boolean): Observable<Category> {
    category.enabled = enabledStatus;
    return this.http.put<Category>(`${this.url}/${category.id}/enabled`, category);
  }

}
