import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeLogoutComponent } from './testee-logout.component';

describe('TesteeLogoutComponent', () => {
  let component: TesteeLogoutComponent;
  let fixture: ComponentFixture<TesteeLogoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeLogoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeLogoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
