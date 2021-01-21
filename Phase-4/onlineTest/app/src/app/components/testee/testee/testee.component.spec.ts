import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeComponent } from './testee.component';

describe('TesteeComponent', () => {
  let component: TesteeComponent;
  let fixture: ComponentFixture<TesteeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
