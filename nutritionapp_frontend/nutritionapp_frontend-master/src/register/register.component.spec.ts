// import { ToastrModule } from 'ngx-toastr';
// import { RouterModule } from '@angular/router';
// import { ReactiveFormsModule } from '@angular/forms';
// import { HttpClientTestingModule } from '@angular/common/http/testing';
// import { ComponentFixture, TestBed } from '@angular/core/testing';

// import { RegisterComponent } from './register.component';

// describe('RegisterComponent', () => {
//   let component: RegisterComponent;
//   let fixture: ComponentFixture<RegisterComponent>;

//   beforeEach(() => {
//     TestBed.configureTestingModule({
//       declarations: [RegisterComponent],
//       imports: [HttpClientTestingModule
//         ,ReactiveFormsModule,
//         RouterModule.forRoot([]),
//         ToastrModule.forRoot() 
//       ] 
//     });
//     fixture = TestBed.createComponent(RegisterComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });
// });











import { ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';
import { RegisterComponent } from './register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { of, throwError } from 'rxjs';
import { AbstractControl } from '@angular/forms';
import { AuthServiceService } from '../services/auth-service.service';
import { RegistrationServiceService } from '../services/registration-service.service';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let registrationServiceSpy: jasmine.SpyObj<RegistrationServiceService>;
  let authServiceSpy: jasmine.SpyObj<AuthServiceService>;
  let toastrServiceSpy: jasmine.SpyObj<ToastrService>;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(() => {
    registrationServiceSpy = jasmine.createSpyObj('RegistrationServiceService', ['register']);
    authServiceSpy = jasmine.createSpyObj('AuthServiceService', ['login']);
    toastrServiceSpy = jasmine.createSpyObj('ToastrService', ['success', 'error']);
    routerSpy = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      imports: [ReactiveFormsModule],
      providers: [
        { provide: RegistrationServiceService, useValue: registrationServiceSpy },
        { provide: AuthServiceService, useValue: authServiceSpy },
        { provide: ToastrService, useValue: toastrServiceSpy },
        { provide: Router, useValue: routerSpy }
      ],
    });

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show success toastr and navigate to /login on successful registration', fakeAsync(() => {
    const fakeData = { /* mock your response data here */ };
    registrationServiceSpy.register.and.returnValue(of(fakeData));

    component.onSubmit();
    tick();

    expect(toastrServiceSpy.success).toHaveBeenCalledWith('Registration completed successfully!', 'Success', { timeOut: 3000 });
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/login'], jasmine.objectContaining({
      skipLocationChange: true,
      queryParams: { data: JSON.stringify(fakeData) },
    }));
  }));

  it('should show error toastr and not navigate on registration failure', fakeAsync(() => {
    const errorMessage = 'Registration failed. Please check the details and try again.';
    registrationServiceSpy.register.and.returnValue(throwError({ error: errorMessage }));

    component.onSubmit();
    tick();

    expect(toastrServiceSpy.error).toHaveBeenCalledWith(errorMessage, 'Error', { timeOut: 3000 });
    expect(routerSpy.navigate).not.toHaveBeenCalled();
  }));

  
  it('should handle invalid form submission', fakeAsync(() => {
    
    component.registrationForm.setValue({ username: '', password: '', email: '' });
    fixture.detectChanges();
    expect(registrationServiceSpy.register).not.toHaveBeenCalled();
  }));



// ... other imports ...

it('should show validation error messages for required fields', () => {
  const usernameControl: AbstractControl | null = component.registrationForm.get('username');
  const passwordControl: AbstractControl | null = component.registrationForm.get('password');
  const emailControl: AbstractControl | null = component.registrationForm.get('email');

  if (usernameControl && passwordControl && emailControl) {
    usernameControl.setValue('');
    passwordControl.setValue('');
    emailControl.setValue('');

    expect(usernameControl.hasError('required')).toBeTruthy();
    expect(passwordControl.hasError('required')).toBeTruthy();
    expect(emailControl.hasError('required')).toBeTruthy();
  } else {
    fail('Form controls are null');
  }
});

it('should show validation error message for password minlength', () => {
  const passwordControl: AbstractControl | null = component.registrationForm.get('password');

  if (passwordControl) {
    passwordControl.setValue('short');

    expect(passwordControl.hasError('minlength')).toBeTruthy();
  } else {
    fail('Password control is null');
  }
});



  it('should not navigate to /wishlist if token does not exist in localStorage on init', () => {
    spyOn(localStorage, 'getItem').and.returnValue(null);
    component.ngOnInit();
    expect(routerSpy.navigate).not.toHaveBeenCalled();
  });

  it('should set error message on registration error', fakeAsync(() => {
    const errorMessage = 'Bad user details';
    spyOn(console, 'log'); 
    registrationServiceSpy.register.and.returnValue(throwError({ error: errorMessage }));

    component.onSubmit();
    tick();

    expect(component.errormsg).toEqual(errorMessage);
    expect(toastrServiceSpy.error).toHaveBeenCalledWith('Registration failed. Please check the details and try again.', 'Error', { timeOut: 3000 });
  }));
});

