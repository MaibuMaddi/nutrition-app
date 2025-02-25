import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodListServiceService {

  constructor(private _http : HttpClient) { }

  public getProducts(query:String):Observable<any>{
   console.log(query);
    return this._http.get("http://localhost:8083/nutrition/searchProduct/"+query);
    
  }
}
