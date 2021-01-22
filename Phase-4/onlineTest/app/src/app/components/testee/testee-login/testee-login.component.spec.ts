import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeLoginComponent } from './testee-login.component';

describe('TesteeLoginComponent', () => {
  let component: TesteeLoginComponent;
  let fixture: ComponentFixture<TesteeLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
