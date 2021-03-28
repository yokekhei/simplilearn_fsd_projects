import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';

import { Common } from 'src/app/core/common';
import { DataService } from './../../../services/data.service';
import { Offer } from './../../../models/offer';
import { OfferService } from './../../../services/offer.service';

@Component({
  selector: 'app-admin-offers',
  templateUrl: './admin-offers.component.html',
  styleUrls: ['./admin-offers.component.scss']
})
export class AdminOffersComponent implements OnInit {

  offers: Offer[] = [];

  constructor(private offerService: OfferService,
              private dataService: DataService,
              private router: Router) { }

  ngOnInit(): void {
    this.getOfferList();
  }

  getOfferList(): void {
    this.offerService.getAllOffers().subscribe(
      offers => {
        this.offers = offers;
        this.dataService.changeOffers(offers);
      }
    );
  }

  onDeleteOffer(id?: number): void {
    if (id === undefined) { return; }

    Swal.fire({
      title: 'Are you sure that you want to remove offer?',
      text: '',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No'
    })
      .then((result) => {
        if (result.value) {
          this.offerService.deleteOffer(id).subscribe(
            (offer: Offer) => Swal.fire(`Offer ${offer.name} removed successfully`,
              '', 'success'),
            (err: any) => Swal.fire(err.error.message, '', 'error'),
            () => {
              this.getOfferList();
              this.router.navigate(['/admin/home/offer']);
            }
          );
        }
      });
  }

  getOfferDiscountType(type: string): string {
    return Common.getOfferDiscountType(type);
  }

}
