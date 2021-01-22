import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeQuizCatalogComponent } from './testee-quiz-catalog.component';

describe('TesteeQuizCatalogComponent', () => {
  let component: TesteeQuizCatalogComponent;
  let fixture: ComponentFixture<TesteeQuizCatalogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeQuizCatalogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeQuizCatalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
