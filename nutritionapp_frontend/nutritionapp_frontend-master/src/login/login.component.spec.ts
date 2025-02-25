// import { RouterModule } from '@angular/router';
// import { ReactiveFormsModule } from '@angular/forms';
// import { HttpClientTestingModule } from '@angular/common/http/testing';
// import { ComponentFixture, TestBed } from '@angular/core/testing';

// import { LoginComponent } from './login.component';

// describe('LoginComponent', () => {
//   let component: LoginComponent;
//   let fixture: ComponentFixture<LoginComponent>;

//   beforeEach(() => {
//     TestBed.configureTestingModule({
//       declarations: [LoginComponent],
//       imports: [HttpClientTestingModule,ReactiveFormsModule,RouterModule.forRoot([])] 
//     });
//     fixture = TestBed.createComponent(LoginComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });
// });










import { ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { UserCredentials } from './../models/user-credentials';
import { LoginServiceService } from './../services/login-service.service';
import { AuthServiceService } from './../services/auth-service.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let loginServiceSpy: jasmine.SpyObj<LoginServiceService>;
  let authServiceSpy: jasmine.SpyObj<AuthServiceService>;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(() => {
    loginServiceSpy = jasmine.createSpyObj('LoginServiceService', ['login']);
    authServiceSpy = jasmine.createSpyObj('AuthServiceService', ['login']);
    routerSpy = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [ReactiveFormsModule],
      providers: [
        { provide: LoginServiceService, useValue: loginServiceSpy },
        { provide: AuthServiceService, useValue: authServiceSpy },
        { provide: Router, useValue: routerSpy }
      ],
    });

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to /wishlist if token exists in localStorage on init', () => {
    spyOn(localStorage, 'getItem').and.returnValue('fakeToken');
    component.ngOnInit();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/wishlist']);
  });

  it('should not navigate to /wishlist if token does not exist in localStorage on init', () => {
    spyOn(localStorage, 'getItem').and.returnValue(null);
    component.ngOnInit();
    expect(routerSpy.navigate).not.toHaveBeenCalled();
  });

  it('should set error message on login error', fakeAsync(() => {
    loginServiceSpy.login.and.returnValue(throwError({ error: 'Invalid credentials' }));
    component.onSubmit();
    tick(); 
    expect(component.errormsg).toEqual('Invalid credentials');
  }));

  
});












