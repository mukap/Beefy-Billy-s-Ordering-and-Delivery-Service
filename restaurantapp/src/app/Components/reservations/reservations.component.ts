import { Component, OnInit } from '@angular/core';
import { Reservations } from '../../Models/reservations';
import { ReservationsService } from '../../Services/reservations.service';
import { CustomersService } from '../../Services/customers.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/Services/auth.service';
import { CustomerClass } from 'src/app/customerclass';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit{

  title = 'Reservation List';

  public employee = false;

  reservationForm: FormGroup;
  customer_id?: any;

  message: string = '';


  constructor(private reservationsService: ReservationsService, private customersService: CustomersService,
    public authService: AuthService, private formBuilder: FormBuilder) {
    
      this.customersService = customersService;

    
      this.reservationForm = this.formBuilder.group({
      firstName: '',
      lastName: '',
      email: '',
      address: '',
      date: '',
      time: ''
    })
  }



  reservationsList: Reservations[] = [];

  getReservations(): void {

    this.reservationsService.getReservations()
    .subscribe(reservationsList => this.reservationsList = reservationsList);

  }

  ngOnInit(): void {
    this.getReservations();
    this.checkUserType();
  }

  checkUserType(): void{
    if(this.authService.isLoggedIn){
      this.employee = true;
    }
  }

  getReservationInfo(){
    console.log(this.reservationForm.value);
    
    console.log(this.reservationForm.get('orderType')?.value);

    var firstName = this.reservationForm.get('firstName')?.value;
    var lastName = this.reservationForm.get('lastName')?.value;
    var email = this.reservationForm.get('email')?.value;
    var address = this.reservationForm.get('address')?.value;

    let customerclass: CustomerClass = new CustomerClass(firstName, lastName, email, address);

    console.log(customerclass);

    // This Posts and Returns the Customer ID
    this.customersService.saveCustomerForm(customerclass)
    .subscribe(customer_id => {this.customer_id = customer_id.body.customer_id}, error => {console.log(error)}, () =>
      this.saveReservations(this.reservationForm.get('date')?.value, this.reservationForm.get('time')?.value ,this.customer_id));
  }

  saveReservations(reservationDate: string, reservationTime: string, id:number): void {

    let reservation: Reservations = {
      reservation_id : -1,//Set as -1 due to Hibernate/SQL generating the ID
      customer_id: id,
      date : reservationDate,
      time : reservationTime,
      status : 'Booked'
    };

    this.reservationsService.saveReservations(reservation)
      .subscribe(resp => { console.log(resp);});

      window.location.reload();

      this.message = 'Order Submitted';
  }

  cancelReservations(id:any): void{
    let resID:number = parseInt(id);
    let status:string = 'Cancelled';

    this.reservationsService.cancelReservation(resID,status)
      .subscribe(resp => {console.log(resp);});

      window.location.reload();
  }

}
