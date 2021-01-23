import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeRegisterComponent } from './testee-register.component';

describe('TesteeRegisterComponent', () => {
  let component: TesteeRegisterComponent;
  let fixture: ComponentFixture<TesteeRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
