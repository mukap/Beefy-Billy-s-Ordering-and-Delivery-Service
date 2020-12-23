import { Component, OnInit } from '@angular/core';

import { OrderClass } from '../../Models/orderclass';

import { OrdersService } from '../../Services/orders.service';
import { FormBuilder, FormGroup } from "@angular/forms";
import { MenuItemClass } from 'src/app/Models/menuitemclass';
import { AuthService } from 'src/app/Services/auth.service';
import { CustomersService} from 'src/app/Services/customers.service';
import { CustomerClass } from 'src/app/customerclass';


@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})




/**
 * The Orders Component deals with viewing and managing the status of orders
 * 
 * @author Justin Kroh
 * */
export class OrdersComponent implements OnInit {

  title ='Orders Page';

  pendingResponse: any;

  form: FormGroup;

  message: string = 'Click the buttons to update the corresponding order';

  customer?: CustomerClass;


  orderList?: OrderClass [];

  constructor(private formBuilder: FormBuilder, private ordersService: OrdersService, private customersService: CustomersService, public authService: AuthService) {
    this.form = this.formBuilder.group({
      orderType: '',
      billingAddress: '',
      deliveryAddress: ''

    })
  }


  //customerList: Customer[] = [];

  

  getMenuItems(): void {

    this.ordersService.getOrders()
    .subscribe(orderList => this.orderList = orderList,  error => {console.log(error)}, () => console.log(this.orderList));

    console.log(this.orderList);
  }



  updateStatusPENDING(order_id: any){

    this.ordersService.updateStatus(order_id.innerHTML, 'PENDING')
    .subscribe(pendingResponse => this.pendingResponse = pendingResponse,  error => {console.log(error)}, () => {console.log(this.pendingResponse), this.getMenuItems()});

    this.message = 'Updated Pending Status for Order' + order_id.innerHTML;

    this.getMenuItems();


  }

  updateStatusDELIVERED(order_id: any){

    this.ordersService.updateStatus(order_id.innerHTML, 'DELIVERED')
    .subscribe(pendingResponse => this.pendingResponse = pendingResponse,  error => {console.log(error)}, () => {console.log(this.pendingResponse), this.getMenuItems()});

    this.message = 'Updated Delivered Status for Order' + order_id.innerHTML;

    this.getMenuItems();

  }

  updateStatusCANCELED(order_id: any){

    this.ordersService.updateStatus(order_id.innerHTML, 'CANCELED')
    .subscribe(pendingResponse => this.pendingResponse = pendingResponse,  error => {console.log(error)}, () => {console.log(this.pendingResponse), this.getMenuItems()});

    this.message = 'Updated Canceled Status for Order' + order_id.innerHTML;

    this.getMenuItems();

  }

  getCustomer(id: any){
    console.log(id.innerHTML);
    this.customersService.getCustomer(id.innerHTML)
    
    .subscribe(customer => this.customer = customer,  error => {console.log(error)}, () => console.log(this.customer));

  }

  


  /*
  submitForm() {

    console.log(this.form.value);
    
    console.log(this.form.get('orderType')?.value);

    var orderType = this.form.get('orderType')?.value;
    var deliveryAddress = this.form.get('deliveryAddress')?.value;
    var billingAddress = this.form.get('billingAddress')?.value;

    var status: string = 'pending';
    var customer_id: number = 3;

    let menuItemObject1: MenuItemClass = new MenuItemClass('pizza', 'pepporonia pizza', 23.34);
    let menuItemObject2: MenuItemClass = new MenuItemClass('calzone', 'peperoni', 12.34);


    var itemsOrdered: MenuItemClass[] = [menuItemObject1, menuItemObject2];

    let orderObject: OrderClass = new OrderClass(orderType, status, customer_id,
       deliveryAddress, billingAddress, itemsOrdered);

    console.log(orderObject);

    // This data is returned data after post just use .whatever field
    this.ordersService.saveOrderForm(orderObject)
    .subscribe((data => {console.log(data.body)}));
  }
*/


  ngOnInit(): void {

    this.getMenuItems();
  }

}
