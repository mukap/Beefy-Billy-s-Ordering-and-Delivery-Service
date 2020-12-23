import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { CustomersComponent } from './Components/customers/customers.component';
import { ReservationsComponent } from './Components/reservations/reservations.component';

import { ReactiveFormsModule } from '@angular/forms';
import { EmployeeComponent } from './Components/employees/employee.component';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { MenuComponent } from './Components/menu/menu.component';
import { MenuItemsComponent } from './Components/menuitems/menuitems.component';
import { OrdersComponent } from './Components/orders/orders.component';
import { CreateOrderComponent } from './Components/create-order/create-order.component';
import { LoginComponent } from './Components/login/login.component';
import { HomepageComponent } from './Components/homepage/homepage.component';
import { FooterComponent } from './Components/footer/footer.component';




@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    EmployeeComponent,
    ReservationsComponent,
    NavbarComponent,
    MenuComponent,
    MenuItemsComponent,
    OrdersComponent,
    CreateOrderComponent,
    LoginComponent,
    HomepageComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
