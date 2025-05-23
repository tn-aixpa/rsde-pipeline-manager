import { TestBed } from '@angular/core/testing';

import { ElaborationService } from './elaboration.service';

describe('ElaborationServiceService', () => {
  let service: ElaborationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ElaborationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
