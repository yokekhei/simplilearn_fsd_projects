import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFoodCreateComponent } from './admin-food-create.component';

describe('AdminFoodCreateComponent', () => {
  let component: AdminFoodCreateComponent;
  let fixture: ComponentFixture<AdminFoodCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminFoodCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminFoodCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
