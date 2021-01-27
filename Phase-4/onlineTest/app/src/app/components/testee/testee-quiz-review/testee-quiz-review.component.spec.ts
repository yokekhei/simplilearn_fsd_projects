import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeQuizReviewComponent } from './testee-quiz-review.component';

describe('TesteeQuizReviewComponent', () => {
  let component: TesteeQuizReviewComponent;
  let fixture: ComponentFixture<TesteeQuizReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeQuizReviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeQuizReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
