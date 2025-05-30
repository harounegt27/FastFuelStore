import { TestBed } from '@angular/core/testing';

import { InternauteServiceService } from './internaute-service.service';

describe('InternauteServiceService', () => {
  let service: InternauteServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InternauteServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
