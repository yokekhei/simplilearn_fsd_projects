import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ChargeRequest } from './../models/charge-request';
import { ChargeResponse } from './../models/charge-response';
import { ConfigurationService } from './configuration.service';

@Injectable({
  providedIn: 'root'
})
export class ChargeService {

  private url = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.url = `${this.configuration.getValue('apiUrl')}/charge`;
  }

  createCharge(chargeRequest: ChargeRequest): Observable<ChargeResponse> {
    return this.http.post<ChargeResponse>(this.url, chargeRequest);
  }

}
