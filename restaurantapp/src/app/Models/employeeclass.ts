export class EmployeeClass {


    firstName: string;
    lastName: string;
    email: string;
    address: string;
    password: string;
    user_type: string;

    constructor(firstName:string, lastName:string, email: string, address: string, password: string, user_type: string){

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.user_type = user_type;

    }

  }