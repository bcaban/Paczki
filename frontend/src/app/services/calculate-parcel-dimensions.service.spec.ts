import {TestBed} from '@angular/core/testing';

import {CalculateParcelDimensionsService} from './calculate-parcel-dimensions.service';

describe('PostDimensionsService', () => {
  let service: CalculateParcelDimensionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculateParcelDimensionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
