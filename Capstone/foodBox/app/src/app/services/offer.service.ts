import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ConfigurationService } from './configuration.service';
import { Offer } from './../models/offer';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  private url = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.url = `${this.configuration.getValue('apiUrl')}/offer`;
  }

  getAllOffers(): Observable<Offer[]> {
    return this.http.get<Offer[]>(`${this.url}`).pipe(
      map(offers => {
        return offers.map(offer => {
          return {
            id: offer.id,
            name: offer.name,
            discountType: offer.discountType,
            discount: offer.discount
          };
        });
      })
    );
  }

  getOfferById(id: number): Observable<Offer> {
    return this.http.get<Offer>(`${this.url}/${id}`);
  }

  createOffer(offer: Offer): Observable<Offer> {
    return this.http.post<Offer>(this.url, offer);
  }

  updateOffer(offer: Offer): Observable<Offer> {
    return this.http.put<Offer>(this.url, offer);
  }

  deleteOffer(id: number): Observable<Offer> {
    return this.http.delete<Offer>(`${this.url}/${id}`);
  }

}
