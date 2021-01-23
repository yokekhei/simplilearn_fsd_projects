import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterLoginComponent } from './tester-login.component';

describe('TesterLoginComponent', () => {
  let component: TesterLoginComponent;
  let fixture: ComponentFixture<TesterLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesterLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
