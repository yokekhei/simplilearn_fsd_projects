import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

import { Offer } from './../../../models/offer';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-admin-offer-update',
  templateUrl: './admin-offer-update.component.html',
  styleUrls: ['./admin-offer-update.component.scss']
})
export class AdminOfferUpdateComponent implements OnInit {

  offer?: Offer;
  name = '';
  discountType = '';
  discount = 0.0;

  constructor(private offerService: OfferService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.offerService.getOfferById(+params.id).subscribe(
        (offer: Offer) => {
          this.offer = offer;
          this.name = offer.name;
          this.discountType = offer.discountType;
          this.discount = offer.discount;
        },
        (err: any) => Swal.fire(err.error.message, '', 'error')
      );
    });
  }

  submit(form: NgForm): void {
    if (this.offer === undefined) { return; }

    this.offer.name = form.form.value.offerName;
    this.offer.discountType = form.form.value.offerDiscountType;
    this.offer.discount = form.form.value.offerDiscount;

    this.offerService.updateOffer(this.offer).subscribe(
      (offer: Offer) => this.offer = offer,
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => this.router.navigate(['/admin/home/offer'])
    );
  }

}
