import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterHomeComponent } from './tester-home.component';

describe('TesterHomeComponent', () => {
  let component: TesterHomeComponent;
  let fixture: ComponentFixture<TesterHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesterHomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
