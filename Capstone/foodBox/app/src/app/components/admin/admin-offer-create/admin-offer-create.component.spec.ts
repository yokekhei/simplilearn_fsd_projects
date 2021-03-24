import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminOfferCreateComponent } from './admin-offer-create.component';

describe('AdminOfferCreateComponent', () => {
  let component: AdminOfferCreateComponent;
  let fixture: ComponentFixture<AdminOfferCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminOfferCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminOfferCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
