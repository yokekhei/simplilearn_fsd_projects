import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ConfigurationService } from './configuration.service';
import { Fee } from './../models/fee';

@Injectable({
  providedIn: 'root'
})
export class FeeService {

  private url = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.url = `${this.configuration.getValue('apiUrl')}/fee`;
  }

  getDeliveryFee(): Observable<Fee> {
    return this.http.get<Fee>(`${this.url}/delivery`);
  }

}
