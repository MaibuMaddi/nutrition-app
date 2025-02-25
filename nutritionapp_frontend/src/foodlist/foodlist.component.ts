import { Router } from '@angular/router';
import { AuthServiceService } from './../services/auth-service.service';
import { WishlistServiceService } from './../services/wishlist-service.service';
import { FoodItem } from './../models/food-item';
import { FoodListServiceService } from './../services/food-list-service.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component } from '@angular/core';

@Component({
  selector: 'app-foodlist',
  templateUrl: './foodlist.component.html',
  styleUrls: ['./foodlist.component.css']
})
export class FoodlistComponent {
  foodItems: FoodItem[] = []; 
  displayedColumns: string[] = ['itemName','brandName','servingQuantity','calories','totalfat','cholesterol','sodium','totalCarbohydrate',
                                    'dietaryFiber','sugars','protein','potassium','addItem'
                                ];

  foodListForm: FormGroup;
  
  constructor(private fb: FormBuilder, private foodListSer:FoodListServiceService,private wishListSer:WishlistServiceService
    , private authService:AuthServiceService , private route : Router 
    ) {
    this.foodListForm = this.fb.group({
      query: ['', [Validators.required]]
    });
  }

  ngOnInit() {}

  onSubmit() {
    const formData = this.foodListForm.value;
    const query: String = formData.query
    console.log('query', query);

    let response=this.foodListSer.getProducts(query);
    response.subscribe(data=>{
      console.log(data);
      this.foodItems=data as FoodItem[];
    },
    error=>{
      console.log("An error Occured while fetching food list",error);
    }
    
    
    );


  }
  addToWishList(fooditem:FoodItem){
      console.log(fooditem)
      const token = localStorage.getItem("token");
      this.wishListSer.AddToWishlist(fooditem,token).subscribe(
          data=>{
            console.log(data);
            this.route.navigate(['/wishlist'],{
              skipLocationChange : false,
          
            });
            
          },
          error=>{
            console.log(error);
          }
      )

  }


  logout(){
    console.log("loggin out")
    localStorage.clear();
    this.authService.logout();
    this.route.navigate(['/login'])
  }


}
