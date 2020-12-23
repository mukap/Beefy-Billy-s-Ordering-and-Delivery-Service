import { Injectable } from '@angular/core';
import { MenuItemClass } from '../Models/menuitemclass';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})


/**
 * MenuItems Endpoint Access
 * 
 * @author Justin Kroh
 * */
export class MenuItemsService {



  private menuItemsUrl = 'http://localhost:8081/restaurantappserver/api/menuitems';

  constructor(private http: HttpClient) { }

  getMenuItems(): Observable<MenuItemClass[]> {

    console.log(this.http.get<MenuItemClass[]>(this.menuItemsUrl));
    return this.http.get<MenuItemClass[]>(this.menuItemsUrl);
  }

  getMenuItem(id: string): Observable<MenuItemClass> {

    console.log(this.http.get<MenuItemClass>(this.menuItemsUrl + '/m/' + id));
    return this.http.get<MenuItemClass>(this.menuItemsUrl + '/m/' + id);

  }

  saveMenuItem(menuitem: MenuItemClass): Observable<any> {
    return this.http.post<any>(this.menuItemsUrl, menuitem, {
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

  saveMenuItemForm(any: any): Observable<any> {
    return this.http.post<any>(this.menuItemsUrl, any, this.httpOptions);
  }

  removeMenuItem(id: string): Observable<any>{
    console.log("Menu ID to remove: " + id);
    console.log(this.menuItemsUrl + '/r/' + id);
    return this.http.delete<any>(this.menuItemsUrl + '/r/' + id);
  }

  updateMenuItemForm(any: any): Observable<any> {
    console.log(any)
    let id:number = any.menu_id;
    let itemName: string = any.itemName;
    let description: string = any.description;
    let price: number = any.price;

    let params:string = `?id=${id}&name=${itemName}&desc=${description}&price=${price}`;
    console.log(params);
    return this.http.put<any>(this.menuItemsUrl + '/u/' + params, this.httpOptions);
  }

  // sortList(menuItemsUnsorted:Observable<Menu[]>) : Observable<Menu[]>{
  //   let menuItemsSorted = menuItemsUnsorted;
  //   menuItemsUnsorted.forEach(item => { item.sort();
  //     console.log(item);
  //     for(var i = 0; i < item.length; i++){
  //       let j = 1
  //       console.log("Menu Item menu_id" + item[i].menu_id);

  //       while(j < item.length)
  //       {
  //         let first = item[i];
  //         let second = item[j];
  //         if(first.menu_id>second.menu_id){
  //           console.log(first.menu_id + " is less than " + second.menu_id)
  //           let tempItem = first;
  //           first = second;
  //           second = tempItem;
  //           console.log(first.menu_id);
  //           console.log(second.menu_id);
  //         }
  //         else
  //         {
  //           console.log(second.menu_id + " is more than " + first.menu_id)
  //           // let tempItem = second;
  //           // second = first;
  //           // first = tempItem;
  //           console.log(first.menu_id);
  //           console.log(second.menu_id);
  //         }
  //         j++
  //       }
  //       j=1;
  //     }
  //     menuItemsSorted = menuItemsUnsorted
  //   });

  //   return menuItemsSorted;
  // }





}
