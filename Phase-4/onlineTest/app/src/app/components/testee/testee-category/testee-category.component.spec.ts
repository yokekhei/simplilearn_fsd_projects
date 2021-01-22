import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesteeCategoryComponent } from './testee-category.component';

describe('TesteeCategoryComponent', () => {
  let component: TesteeCategoryComponent;
  let fixture: ComponentFixture<TesteeCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesteeCategoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesteeCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
