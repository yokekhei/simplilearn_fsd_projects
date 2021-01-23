import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterRegisterComponent } from './tester-register.component';

describe('TesterRegisterComponent', () => {
  let component: TesterRegisterComponent;
  let fixture: ComponentFixture<TesterRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesterRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
