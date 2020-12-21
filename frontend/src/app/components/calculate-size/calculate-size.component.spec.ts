import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculateSizeComponent } from './calculate-size.component';

describe('CalculateSizeComponent', () => {
  let component: CalculateSizeComponent;
  let fixture: ComponentFixture<CalculateSizeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalculateSizeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalculateSizeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
