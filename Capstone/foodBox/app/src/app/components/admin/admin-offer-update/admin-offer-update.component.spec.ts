import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminOfferUpdateComponent } from './admin-offer-update.component';

describe('AdminOfferUpdateComponent', () => {
  let component: AdminOfferUpdateComponent;
  let fixture: ComponentFixture<AdminOfferUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminOfferUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminOfferUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
