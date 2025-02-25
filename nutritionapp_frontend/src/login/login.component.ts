import { AuthServiceService } from './../services/auth-service.service';
import { Router } from '@angular/router';
import { UserCredentials } from './../models/user-credentials';
import { LoginServiceService } from './../services/login-service.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  loginForm: FormGroup;
  errormsg:any='';
  constructor(private fb: FormBuilder, private loginSer:LoginServiceService, private route : Router 
    , private authService:AuthServiceService
    ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(2)]],
    });
  }

  ngOnInit() {
    if(localStorage.getItem("token")!==null){
      this.route.navigate(['/wishlist'])
    }

  }

  onSubmit() {
    const loginData = this.loginForm.value;
    const user: UserCredentials = new UserCredentials(
      loginData.username,
      loginData.password
    );
    console.log('User Login Data:', user);




    // this.loginSer.login(user).subscribe(
    //   (data: any) => {
    //     console.log("response received");
    //     console.log(data);
    //     const token = data.token;
    //     console.log(token);
    
    //     localStorage.setItem("token", token);
    //     this.authService.login();
    
    //     console.log(localStorage.getItem("token"));
    //     this.route.navigate(['/wishlist'], {
    //       skipLocationChange: false,
    //       queryParams: {
    //         data: JSON.stringify(data)
    //       }
    //     });
    //   },
    //   error => {
    //     console.log("Error while doing login", error);
    //     this.errormsg = error.error;
    //   }
    // );
    






    this.loginSer.login(user).subscribe(
      data =>{
        console.log("response received");
        console.log(data);
        const parsedData = JSON.parse(data);
        const token = parsedData.token;
        console.log(token);
            
        localStorage.setItem("token",token);
        this.authService.login();
        
        console.log(localStorage.getItem("token"));
        this.route.navigate(['/wishlist'],{
          skipLocationChange : false,
          queryParams : {
            data : JSON.stringify(data)
          }
        });
        
      },
      error=>{
        console.log("Error while doing login",error);
        this.errormsg=error.error;
      }
      );



  }

  

}
