import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAccessComponent } from './update-access.component';

describe('UpdateAccessComponent', () => {
  let component: UpdateAccessComponent;
  let fixture: ComponentFixture<UpdateAccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateAccessComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateAccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
