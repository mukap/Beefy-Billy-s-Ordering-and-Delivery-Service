import { Component, OnInit } from '@angular/core';
import { Employee } from '../../Models/employee';
import { EmployeeClass } from '../../Models/employeeclass';


import { EmployeeService } from '../../Services/employee.service';
import { FormBuilder, FormGroup } from "@angular/forms";



@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  title = 'Employee List';

  employee: Employee ={
    employee_id:1,
    password: 'passwordtest',
    email: 'emailtest@cc.gmail',
    firstName: 'firstnametest',
    lastName: 'lastnametest',
    address: 'addresstest',
    user_type: 'usertypetest',
  };

  form: FormGroup;

  constructor(private formBuilder: FormBuilder,private employeeService:EmployeeService) {
    this.form = this.formBuilder.group({
      firstName: '',
      lastName: '',
      email: '',
      address: '',
      password: '',
      usertype: ''
    })
   }

  employeeList: Employee[] = [];

  submitForm() {

    console.log(this.form.value);
    
    console.log(this.form.get('firstName')?.value);

    var firstName = this.form.get('firstName')?.value;
    var lastName = this.form.get('lastName')?.value;
    var email = this.form.get('email')?.value;
    var address = this.form.get('address')?.value;
    var password = this.form.get('password')?.value;
    var usertype = this.form.get('usertype')?.value;

    let employeeclass: EmployeeClass = new EmployeeClass(firstName, lastName, email, address, password, usertype);

    console.log(employeeclass);

    this.employeeService.saveEmployeeForm(employeeclass)
    .subscribe((data => {console.log(data.body)}));

    window.location.reload();
    //.subscribe((data => {console.log(data.body.employee_id)}));
  }


  getEmployees(): void{
    this.employeeService.getEmployees()
    .subscribe(employeeList => this.employeeList = employeeList);

  }

  removeEmployee(id: any): void{
    let empID: string = id.toString();
    this.employeeService.removeEmployee(empID)
      .subscribe(resp => console.log(resp));

    window.location.reload();
  }

  // saveEmployee(employee: Employee): void{
  //   this.employeeService.saveEmployee(employee)
  //   .subscribe(rep => console.log(rep));
  // }

  ngOnInit(): void {
    this.getEmployees();
    //this.saveEmployee(this.employee);
  }

}
