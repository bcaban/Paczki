import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelStatusUpdateComponent } from './parcel-status-update.component';

describe('ParcelStatusUpdateComponent', () => {
  let component: ParcelStatusUpdateComponent;
  let fixture: ComponentFixture<ParcelStatusUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParcelStatusUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelStatusUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
