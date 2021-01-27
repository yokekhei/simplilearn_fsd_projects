import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeQuizResultComponent } from './testee-quiz-result.component';

describe('TesteeQuizResultComponent', () => {
  let component: TesteeQuizResultComponent;
  let fixture: ComponentFixture<TesteeQuizResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeQuizResultComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeQuizResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
