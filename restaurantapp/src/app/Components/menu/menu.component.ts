import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import {MenuItemClass} from '../../Models/menuitemclass';
import {MenuItemsService} from '../../Services/menuitems.service'

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menuItems: MenuItemClass[] = [];
  form: FormGroup;

  constructor(private menuItemsService : MenuItemsService, private formBuilder: FormBuilder, public router: Router) {
    this.form = this.formBuilder.group({
      menu_id: -1,
      itemName: '',
      description: '',
      price: 0

    })
   }

  ngOnInit(): void {
    this.getMenuItems();
  }

  getMenuItems(): void{
      this.menuItemsService.getMenuItems()
        .subscribe(menuItems => this.menuItems = menuItems);
  }

  removeMenuItem(id: any): void{
    let menu_id: string =id.toString();
    this.menuItemsService.removeMenuItem(menu_id)
      .subscribe(resp => console.log(resp));
    
      window.location.reload();
  }

  submitForm() {

    console.log(this.form.value);
    
    console.log(this.form.get('itemName')?.value);

    var menu_id = this.form.get('menu_id')?.value;
    var itemName = this.form.get('itemName')?.value;
    var description = this.form.get('description')?.value;
    var price = this.form.get('price')?.value;

    // console.log(menu_id);
    // console.log(itemName);
    // console.log(description);
    // console.log(price);


    let menuItemObject: MenuItemClass = new MenuItemClass(itemName, description, price, menu_id);

    console.log(menuItemObject);



    // This data is returned data after post just use .whatever field
    this.menuItemsService.updateMenuItemForm(menuItemObject)
    .subscribe((resp => {console.log(resp)}));

    window.location.reload();
  }

  // updateMenuItem(id: any): void{
  //   let menu_id: string =id.toString();
  //   this.menuItemsService.updateMenuItem(menu_id)
  //     .subscribe(resp => console.log(resp));
    
  //     window.location.reload();
  // }

}
