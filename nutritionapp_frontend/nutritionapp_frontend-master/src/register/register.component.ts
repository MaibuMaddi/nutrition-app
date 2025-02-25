import { ToastrService } from 'ngx-toastr';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthServiceService } from './../services/auth-service.service';
import { RegistrationServiceService } from './../services/registration-service.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  registrationForm : FormGroup;
  errormsg='';

  constructor(private fb: FormBuilder , private registerSer:RegistrationServiceService ,  private route : Router , private authService:AuthServiceService
    ,private toastr: ToastrService
    ) {
    this.registrationForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      email :['', [Validators.required]]
      // Add more fields as needed
    });
  }

  ngOnInit() {}

  onSubmit() {
    const user = this.registrationForm.value;
    console.log('User Registration Data:', user);
    this.registerSer.register(user).subscribe(
      data =>{
        console.log("response received");
        console.log("------",data);
        // this.registerSer.register(user);
        this.toastr.success('Registration completed successfully!', 'Success', { timeOut: 3000 });
        // this.toastr.success('Successfully done :)','Registration');
        console.log("---registration completed---")
        this.route.navigate(['/login']
        ,{
          skipLocationChange : true,
          queryParams : {
            data : JSON.stringify(data)
          } 
        }
        );
      },
      error =>{
        console.log("Bad user details");
        this.errormsg=error.error;
        ;
        this.toastr.error('Registration failed. Please check the details and try again.', 'Error', { timeOut: 3000 });
      }
   );
  }
}
