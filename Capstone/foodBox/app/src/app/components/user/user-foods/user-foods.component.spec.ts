import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserFoodsComponent } from './user-foods.component';

describe('UserFoodsComponent', () => {
  let component: UserFoodsComponent;
  let fixture: ComponentFixture<UserFoodsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserFoodsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserFoodsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
