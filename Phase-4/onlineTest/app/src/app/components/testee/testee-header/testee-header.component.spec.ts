import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeHeaderComponent } from './testee-header.component';

describe('TesteeHeaderComponent', () => {
  let component: TesteeHeaderComponent;
  let fixture: ComponentFixture<TesteeHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeHeaderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
