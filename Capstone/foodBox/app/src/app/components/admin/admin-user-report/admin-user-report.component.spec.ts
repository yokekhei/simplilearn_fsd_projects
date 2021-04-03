import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUserReportComponent } from './admin-user-report.component';

describe('AdminUserReportComponent', () => {
  let component: AdminUserReportComponent;
  let fixture: ComponentFixture<AdminUserReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminUserReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminUserReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
