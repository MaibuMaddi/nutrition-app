// import { HttpClientTestingModule } from '@angular/common/http/testing';
// import { TestBed } from '@angular/core/testing';

// import { LoginServiceService } from './login-service.service';

// describe('LoginServiceService', () => {
//   let service: LoginServiceService;

//   beforeEach(() => {
//     TestBed.configureTestingModule({
//       imports: [HttpClientTestingModule]
//     });
    
//     service = TestBed.inject(LoginServiceService);
//   });

//   it('should be created', () => {
//     expect(service).toBeTruthy();
//   });
// });





import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { LoginServiceService } from './login-service.service';
import { UserCredentials } from '../models/user-credentials';

describe('LoginServiceService', () => {
  let service: LoginServiceService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [LoginServiceService],
    });
    service = TestBed.inject(LoginServiceService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a POST request for login', () => {
    const mockUser: UserCredentials = { username: 'testuser', password: 'testpassword' };
    const mockResponse = 'mockToken';

    service.login(mockUser).subscribe((data) => {
      expect(data).toEqual(mockResponse);
    });

    const req = httpTestingController.expectOne('http://localhost:8082/auth/login');
    expect(req.request.method).toEqual('POST');
    req.flush(mockResponse);
  });

  it('should send a GET request for getting username', () => {
    const mockToken = 'mockToken';
    const mockResponse = 'testuser';

    service.getUsername(mockToken).subscribe((data) => {
      expect(data).toEqual(mockResponse);
    });

    const req = httpTestingController.expectOne('http://localhost:8082/auth/getUsername');
    expect(req.request.method).toEqual('GET');
    expect(req.request.headers.get('Authorization')).toEqual(mockToken);
    req.flush(mockResponse);
  });
});

