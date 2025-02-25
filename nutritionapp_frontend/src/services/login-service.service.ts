import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserCredentials } from './../models/user-credentials';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {
  try:any;
  constructor(private _http : HttpClient) { }

  public login(user :UserCredentials):Observable<any>{
    sessionStorage.setItem("key" , user.username);
   console.log(user);
    return this._http.post("http://localhost:8082/auth/login",user, {responseType: 'text' as 'json' });
    
  }

  public getUsername(token : string):Observable<any>{
    const headers =new HttpHeaders().set('Authorization',token);
    // const response = this._http.get("http://localhost:8082/auth/getUsername",{headers ,  responseType: 'text'});
    // response.subscribe(
    //   data=>{
    //       console.log(data);
    //   }
    // )
    // console.log(response);
    return this._http.get("http://localhost:8082/auth/getUsername",{headers ,  responseType: 'text'});
    
  }

 

}
