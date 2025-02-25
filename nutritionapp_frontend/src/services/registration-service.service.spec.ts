import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { RegistrationServiceService } from './registration-service.service';

describe('RegistrationServiceService', () => {
  let service: RegistrationServiceService;

 

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [RegistrationServiceService]
    });

    service = TestBed.inject(RegistrationServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
