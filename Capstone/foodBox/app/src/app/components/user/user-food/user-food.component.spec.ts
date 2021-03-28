import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserFoodComponent } from './user-food.component';

describe('UserFoodComponent', () => {
  let component: UserFoodComponent;
  let fixture: ComponentFixture<UserFoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserFoodComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserFoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
