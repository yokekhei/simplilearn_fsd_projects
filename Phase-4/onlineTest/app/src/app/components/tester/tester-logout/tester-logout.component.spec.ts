import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterLogoutComponent } from './tester-logout.component';

describe('TesterLogoutComponent', () => {
  let component: TesterLogoutComponent;
  let fixture: ComponentFixture<TesterLogoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesterLogoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterLogoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
