import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterQuizzesComponent } from './tester-quizzes.component';

describe('TesterQuizzesComponent', () => {
  let component: TesterQuizzesComponent;
  let fixture: ComponentFixture<TesterQuizzesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesterQuizzesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterQuizzesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
