import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/auth/auth.guard';


import {CustomersComponent} from './Components/customers/customers.component';
import {EmployeeComponent} from './Components/employees/employee.component';
import { MenuComponent } from './Components/menu/menu.component';
import {ReservationsComponent} from './Components/reservations/reservations.component';
import {MenuItemsComponent} from './Components/menuitems/menuitems.component';
import {OrdersComponent} from './Components/orders/orders.component';
import {CreateOrderComponent} from './Components/create-order/create-order.component';
import { LoginComponent } from './Components/login/login.component';
import {HomepageComponent} from './Components/homepage/homepage.component';

const routes: Routes = [
  { path: 'Customers', component: CustomersComponent },
  { path: 'Employee',  canActivate: [AuthGuard], component: EmployeeComponent },
  { path: 'Reservation', component: ReservationsComponent },

  { path: 'Menu', component: MenuComponent},
  { path: 'Menu/newItem', component: MenuItemsComponent },
  { path: 'Orders/ManageOrders', canActivate: [AuthGuard], component: OrdersComponent },
  { path: 'Orders/CreateNewOrder', component: CreateOrderComponent },
  { path: 'Login', component: LoginComponent},
  { path: '', component: HomepageComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
