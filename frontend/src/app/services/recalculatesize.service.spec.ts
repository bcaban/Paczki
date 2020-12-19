import { TestBed } from '@angular/core/testing';
import { RecalculateSizeService } from './recalculatesize.service';

describe('RecalculateSizeService', () => {
  let service: RecalculateSizeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecalculateSizeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
