import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminOrderReportComponent } from './admin-order-report.component';

describe('AdminOrderReportComponent', () => {
  let component: AdminOrderReportComponent;
  let fixture: ComponentFixture<AdminOrderReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminOrderReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminOrderReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
