import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFoodUpdateComponent } from './admin-food-update.component';

describe('AdminFoodUpdateComponent', () => {
  let component: AdminFoodUpdateComponent;
  let fixture: ComponentFixture<AdminFoodUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminFoodUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminFoodUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
