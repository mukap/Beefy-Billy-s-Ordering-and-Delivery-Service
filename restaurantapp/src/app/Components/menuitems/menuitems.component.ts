import { Component, OnInit } from '@angular/core';
import { MenuItemClass } from '../../Models/menuitemclass';

import { MenuItemsService } from '../../Services/menuitems.service';
import { FormBuilder, FormGroup } from "@angular/forms";


@Component({
  selector: 'app-menuitems',
  templateUrl: './menuitems.component.html',
  styleUrls: ['./menuitems.component.css']
})
export class MenuItemsComponent implements OnInit {

  title = 'Menu Item Page';

  form: FormGroup;


  constructor(private formBuilder: FormBuilder, private menuItemsService: MenuItemsService) {
    this.form = this.formBuilder.group({
      itemName: '',
      description: '',
      price: 0

    })

   }

  //customerList: Customer[] = [];

  submitForm() {

    console.log(this.form.value);
    
    console.log(this.form.get('itemName')?.value);

    var itemName = this.form.get('itemName')?.value;
    var description = this.form.get('description')?.value;
    var price = this.form.get('price')?.value;


    let menuItemObject: MenuItemClass = new MenuItemClass(itemName, description, price);

    console.log(menuItemObject);



    // This data is returned data after post just use .whatever field
    this.menuItemsService.saveMenuItemForm(menuItemObject)
    .subscribe((data => {console.log(data.body)}));

    window.location.reload();
  }


  ngOnInit(): void {
  }

}
