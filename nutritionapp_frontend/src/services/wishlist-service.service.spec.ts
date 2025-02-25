import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { WishlistServiceService } from './wishlist-service.service';

describe('WishlistServiceService', () => {
  let service: WishlistServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(WishlistServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
