import { LoginServiceService } from './../services/login-service.service';
import { Router } from '@angular/router';
import { AuthServiceService } from './../services/auth-service.service';
import { FoodItem } from './../models/food-item';
import { WishlistServiceService } from './../services/wishlist-service.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit{
  wishList: FoodItem[] = []; 
  displayedColumns: string[] = ['itemName','brandName','servingQuantity','calories','totalfat','cholesterol','sodium','totalCarbohydrate',
                                    'dietaryFiber','sugars','protein','potassium','delete'
                                ];

  wishListForm: FormGroup;
  token:any;
  username!:string;
  constructor(private fb: FormBuilder, private wishListSer : WishlistServiceService , private authService:AuthServiceService
    , private route : Router , private loginSer : LoginServiceService
    ) {
    this.wishListForm = this.fb.group({
      query: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.getUsername();
    this.onSubmit();
  }

  onSubmit() {
    const formData = this.wishListForm.value;
    const query = formData.query
    console.log('query', query);
    this.token=localStorage.getItem("token");
    
    let response=this.wishListSer.getWishlist(this.token);
    response.subscribe(data=>{
      console.log(data);
      this.wishList=data as FoodItem[];
    });
    
  }

  getUsername(){
    this.token=localStorage.getItem("token");
    this.loginSer.getUsername(this.token).subscribe(
      data=>{
        this.username=data;
      },
      error=>{
        console.log("An error occured while fetching username");
      }
      
    )
  }

  

  deleteFoodItemFromWishList(fooditem:FoodItem){
    console.log(fooditem);
    console.log(fooditem.nix_item_id);
    const itemid = fooditem.nix_item_id;
    this.token=localStorage.getItem("token");
    this.wishListSer.deleteFromWishList(itemid,this.token).subscribe(
      data=>{
        console.log(data);
        this.ngOnInit();
      },
      error=>{
        console.log(error);
      }
    );

  }

  addNewProducts(){
    this.route.navigate(['/search'],{
      skipLocationChange : false,
  
    }
    );
  }

  

  logout(){
    console.log("loggin out")
    localStorage.clear();
    this.authService.logout();
    this.route.navigate(['/login'])
  }

}






