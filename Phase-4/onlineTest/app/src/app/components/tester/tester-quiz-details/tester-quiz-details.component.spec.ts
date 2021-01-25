import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterQuizDetailsComponent } from './tester-quiz-details.component';

describe('TesterQuizDetailsComponent', () => {
  let component: TesterQuizDetailsComponent;
  let fixture: ComponentFixture<TesterQuizDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesterQuizDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterQuizDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
