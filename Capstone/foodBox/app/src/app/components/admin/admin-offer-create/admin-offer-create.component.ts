import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

import { Offer } from './../../../models/offer';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-admin-offer-create',
  templateUrl: './admin-offer-create.component.html',
  styleUrls: ['./admin-offer-create.component.scss']
})
export class AdminOfferCreateComponent implements OnInit {

  offer?: Offer;

  constructor(private offerService: OfferService, private router: Router) { }

  ngOnInit(): void {
  }

  submit(form: NgForm): void {
    this.offer = {
      name: form.form.value.offerName,
      discountType: form.form.value.offerDiscountType,
      discount: form.form.value.offerDiscount
    };

    this.offerService.createOffer(this.offer).subscribe(
      (offer: Offer) => this.offer = offer,
      (err: any) => Swal.fire(err.error.message, '', 'error'),
      () => this.router.navigate(['/admin/home/offer'])
    );
  }

}
