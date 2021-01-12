import {TestBed} from '@angular/core/testing';

import {ParcelStatusTranslatorService} from './parcel-status-translator.service';

describe('ParcelStatusTranslatorService', () => {
  let service: ParcelStatusTranslatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParcelStatusTranslatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
