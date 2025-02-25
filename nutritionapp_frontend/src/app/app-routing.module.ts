import { AuthgaurdGuard } from './../services/authgaurd.guard';
import { WishlistComponent } from './../wishlist/wishlist.component';
import { FoodlistComponent } from './../foodlist/foodlist.component';
import { RegisterComponent } from './../register/register.component';
import { LoginComponent } from './../login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [

  {path:'login', component:LoginComponent},
  {path:'register', component:RegisterComponent},
  {path:'search', component:FoodlistComponent,canActivate:[AuthgaurdGuard]},
  {path:'wishlist', component:WishlistComponent,canActivate:[AuthgaurdGuard]},
  {path:'**',redirectTo:'/login',pathMatch:'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
