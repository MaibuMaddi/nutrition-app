// import { ReactiveFormsModule } from '@angular/forms';
// import { MatTableModule } from '@angular/material/table';
// import { HttpClientTestingModule } from '@angular/common/http/testing';
// import { ComponentFixture, TestBed } from '@angular/core/testing';

// import { FoodlistComponent } from './foodlist.component';

// describe('FoodlistComponent', () => {
//   let component: FoodlistComponent;
//   let fixture: ComponentFixture<FoodlistComponent>;

//   beforeEach(() => {
//     TestBed.configureTestingModule({
//       declarations: [FoodlistComponent],
//       imports: [HttpClientTestingModule,MatTableModule,ReactiveFormsModule] 
//     });
//     fixture = TestBed.createComponent(FoodlistComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });
// });


































import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { FoodlistComponent } from './foodlist.component';
import { FoodListServiceService } from './../services/food-list-service.service';
import { WishlistServiceService } from './../services/wishlist-service.service';
import { AuthServiceService } from './../services/auth-service.service';
import { FoodItem } from './../models/food-item';
import { Router } from '@angular/router';

describe('FoodlistComponent', () => {
  let component: FoodlistComponent;
  let fixture: ComponentFixture<FoodlistComponent>;
  let mockFoodListService: jasmine.SpyObj<FoodListServiceService>;
  let mockWishlistService: jasmine.SpyObj<WishlistServiceService>;
  let mockAuthService: jasmine.SpyObj<AuthServiceService>;
  let mockRouter: jasmine.SpyObj<Router>;

  beforeEach(() => {
    mockFoodListService = jasmine.createSpyObj('FoodListServiceService', ['getProducts']);
    mockWishlistService = jasmine.createSpyObj('WishlistServiceService', ['AddToWishlist']);
    mockAuthService = jasmine.createSpyObj('AuthServiceService', ['logout']);
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      declarations: [FoodlistComponent],
      imports: [ReactiveFormsModule, RouterTestingModule],
      providers: [
        { provide: FoodListServiceService, useValue: mockFoodListService },
        { provide: WishlistServiceService, useValue: mockWishlistService },
        { provide: AuthServiceService, useValue: mockAuthService },
        { provide: Router, useValue: mockRouter },
      ],
    });

    fixture = TestBed.createComponent(FoodlistComponent);
    component = fixture.componentInstance;
  });

    

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize form with query control', () => {
    expect(component.foodListForm.get('query')).toBeTruthy();
  });

 

  it('should handle error in onSubmit()', fakeAsync(() => {
    mockFoodListService.getProducts.and.returnValue(throwError('Error'));

    component.onSubmit();
    tick();

    expect(mockFoodListService.getProducts).toHaveBeenCalled();
    expect(component.foodItems).toEqual([]);
  }));

  it('should call addToWishList() and navigate to wishlist on successful response', fakeAsync(() => {
    const mockFoodItem: FoodItem = {
      nix_brand_name: 'Brand B',
      nix_item_name: 'Item B',
      nix_brand_id: 'brandId',
      nix_item_id: 'itemId',
      serving_qty: 1,
      serving_unit: 'piece',
      serving_weight_grams: 120,
      nf_metric_qty: 1,
      nf_metric_uom: 'g',
      nf_calories: 120,
      nf_total_fat: 6,
      nf_saturated_fat: 3,
      nf_cholesterol: 15,
      nf_sodium: 220,
      nf_total_carbohydrate: 25,
      nf_dietary_fiber: 4,
      nf_sugars: 6,
      nf_protein: 9,
      nf_potassium: 320,
    };

    const mockToken = 'mockToken';
    mockWishlistService.AddToWishlist.and.returnValue(of('success'));
    localStorage.setItem('token', mockToken);

    component.addToWishList(mockFoodItem);
    tick();

    expect(mockWishlistService.AddToWishlist).toHaveBeenCalledWith(mockFoodItem, mockToken);
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/wishlist'], { skipLocationChange: false });
  }));

  it('should handle error in addToWishList()', fakeAsync(() => {
    const mockFoodItem: FoodItem = {
      nix_brand_name: 'Brand C',
      nix_item_name: 'Item C',
      nix_brand_id: 'brandId',
      nix_item_id: 'itemId',
      serving_qty: 1,
      serving_unit: 'slice',
      serving_weight_grams: 150,
      nf_metric_qty: 1,
      nf_metric_uom: 'g',
      nf_calories: 150,
      nf_total_fat: 8,
      nf_saturated_fat: 4,
      nf_cholesterol: 20,
      nf_sodium: 250,
      nf_total_carbohydrate: 30,
      nf_dietary_fiber: 5,
      nf_sugars: 7,
      nf_protein: 10,
      nf_potassium: 350,
    };

    const mockToken = 'mockToken';
    mockWishlistService.AddToWishlist.and.returnValue(throwError('Error'));
    localStorage.setItem('token', mockToken);

    component.addToWishList(mockFoodItem);
    tick();

    expect(mockWishlistService.AddToWishlist).toHaveBeenCalledWith(mockFoodItem, mockToken);
    
  }));

  it('should call logout() and clear localStorage on logout', () => {
    spyOn(localStorage, 'clear');
    
    component.logout();

    expect(localStorage.clear).toHaveBeenCalled();
    expect(mockAuthService.logout).toHaveBeenCalled();
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/login']);
  });

  

  
});
