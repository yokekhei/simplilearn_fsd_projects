import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeQuizStepsComponent } from './testee-quiz-steps.component';

describe('TesteeQuizStepsComponent', () => {
  let component: TesteeQuizStepsComponent;
  let fixture: ComponentFixture<TesteeQuizStepsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeQuizStepsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeQuizStepsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
