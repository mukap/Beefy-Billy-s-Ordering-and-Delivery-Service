export class MenuItemClass {

    menu_id?: number;
    itemName: string;
    description: string;
    price: number;


    constructor(itemName:string, description:string, price: number, menu_id?: number){
    
        this.menu_id = menu_id;
        this.itemName = itemName;
        this.description = description;
        this.price = price;

    }

  }