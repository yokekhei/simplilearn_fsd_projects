import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterQuizUpdateComponent } from './tester-quiz-update.component';

describe('TesterQuizUpdateComponent', () => {
  let component: TesterQuizUpdateComponent;
  let fixture: ComponentFixture<TesterQuizUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesterQuizUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterQuizUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
