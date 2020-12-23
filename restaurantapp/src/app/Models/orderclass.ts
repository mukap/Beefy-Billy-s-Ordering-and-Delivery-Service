import {MenuItemClass} from './menuitemclass';

export class OrderClass {

    order_id?: number;
    orderType: string;
    status: string;
    date?: any;
    customer_id: number;
    deliveryAddress: string;
    billingAddress: string;
    itemsOrdered: MenuItemClass[];



    constructor(orderType: string, status:string, customer_id: number,
         deliveryAddress: string, billingAddress: string, itemsOrdered: MenuItemClass[], date?: any, order_id?:number){

        this.order_id = order_id;
        this.date = date;
        this.orderType = orderType;
        this.status = status;
        this.customer_id = customer_id;
        this.deliveryAddress = deliveryAddress;
        this.billingAddress = billingAddress;
        this.itemsOrdered = itemsOrdered;

    }




  }