import {TestBed} from '@angular/core/testing';

import {ParcelService} from './parcel.service';

describe('ParcelServiceService', () => {
  let service: ParcelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParcelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
