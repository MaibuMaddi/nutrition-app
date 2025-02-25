import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  loggedIn : boolean =false;

  login(){
    this.loggedIn=true;
  }

  logout(){
    this.loggedIn=false;
  }

  isLoggedIn():boolean{
    return localStorage.getItem("token")!==null
  }
  constructor() { 
    
    
  }
}
