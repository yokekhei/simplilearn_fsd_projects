import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Common } from 'src/app/core/common';
import { ConfigurationService } from './configuration.service';
import { Food } from '../models/food';
import { Foods } from './../models/foods';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  private url = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.url = `${this.configuration.getValue('apiUrl')}/food`;
  }

  getAllFoods(pageNumber: number, pageSize: number,
              sortBy: string | undefined,
              sortDirection: string | undefined): Observable<Foods> {
    let params = new HttpParams();
    params = params.append('page', '' + pageNumber);
    params = params.append('size', '' + pageSize);
    params = params.append('sortBy', sortBy || Common.SORT_BY_NAME);
    params = params.append('direction', sortDirection || Common.SORT_DIRECTION_ASC);

    return this.http.get<Foods>(`${this.url}`, { params }).pipe(
      map(foods => {
        const retFoods: Foods = {
          list: foods.list.map(food => {
            return {
              id: food.id,
              name: food.name,
              categoryId: food.categoryId,
              price: food.price,
              desc: food.desc,
              offerId: food.offerId,
              createdDateTime: food.createdDateTime,
              enabled: food.enabled
            };
          }),
          pageInfo: foods.pageInfo
        };

        return retFoods;
      })
    );
  }

  deleteFood(id: number): Observable<Food> {
    return this.http.delete<Food>(`${this.url}/${id}`);
  }

  setFoodEnabled(food: Food, enabledStatus: boolean): Observable<Food> {
    food.enabled = enabledStatus;
    return this.http.put<Food>(`${this.url}/${food.id}/enabled`, food);
  }

}
