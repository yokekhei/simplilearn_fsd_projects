import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeQuizComponent } from './testee-quiz.component';

describe('TesteeQuizComponent', () => {
  let component: TesteeQuizComponent;
  let fixture: ComponentFixture<TesteeQuizComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeQuizComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeQuizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
