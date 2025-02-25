import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { AuthgaurdGuard } from './authgaurd.guard';

describe('AuthgaurdGuard', () => {
  let guard: AuthgaurdGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
    });
    guard = TestBed.inject(AuthgaurdGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
