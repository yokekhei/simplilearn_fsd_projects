import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterHeaderComponent } from './tester-header.component';

describe('TesterHeaderComponent', () => {
  let component: TesterHeaderComponent;
  let fixture: ComponentFixture<TesterHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesterHeaderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
