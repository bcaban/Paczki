import { TestBed } from '@angular/core/testing';

import { PostDimensionsService } from './post-dimensions.service';

describe('PostDimensionsService', () => {
  let service: PostDimensionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PostDimensionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
