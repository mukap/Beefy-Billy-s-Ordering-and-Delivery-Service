import { Injectable } from '@angular/core';
import { OrderClass } from '../Models/orderclass';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})



/**
 * Orders Endpoint Access
 * 
 * @author Justin Kroh
 * */
export class OrdersService {

  private ordersUrl = 'http://localhost:8081/restaurantappserver/api/orders';

  constructor(private http: HttpClient) { }

  getOrders(): Observable<OrderClass[]> {

    console.log(this.http.get<OrderClass[]>(this.ordersUrl));
    return this.http.get<OrderClass[]>(this.ordersUrl);
  }

  saveOrder(order: OrderClass): Observable<any> {
    return this.http.post<any>(this.ordersUrl, order, {
      observe: 'response'
    });
  }

//This is how it is done to grab respone data from posts
   httpOptions: any = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    observe: 'response'
  };

  saveOrderForm(any: any): Observable<any> {
    return this.http.post<any>(this.ordersUrl, any, this.httpOptions);
  }

  updateStatus(id:number, status:string): Observable<any>{
    let params:string = `?id=${id}&status=${status}`;
    console.log(params);
    return this.http.put<any>(this.ordersUrl + '/u/' + params, this.httpOptions);
  }
}

