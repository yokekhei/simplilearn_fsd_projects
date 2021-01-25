import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterQuizCreateComponent } from './tester-quiz-create.component';

describe('TesterQuizCreateComponent', () => {
  let component: TesterQuizCreateComponent;
  let fixture: ComponentFixture<TesterQuizCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesterQuizCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterQuizCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
