import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservations } from '../Models/reservations';

@Injectable({
  providedIn: 'root'
})


/**
 * Reservations Api Endpoint
 * 
 * @author Justin Kroh
 * */
export class ReservationsService {


  private justinsurl: string = 'http://localhost:8081/restaurantappserver/api/reservations';

  private reservationsUrl = this.justinsurl;

  httpOptions: any = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    observe: 'response'
  };

  constructor(private http: HttpClient) { }

  getReservations(): Observable<Reservations[]> {

    console.log(this.http.get<Reservations[]>(this.reservationsUrl));
    return this.http.get<Reservations[]>(this.reservationsUrl);
  }

  saveReservations(Reservations: Reservations): Observable<any>{
    return this.http.post<any>(this.reservationsUrl, Reservations, {
      observe: 'response'
    })
  }

  cancelReservation(id:number, status:string): Observable<any>{
    let params:string = `?id=${id}&status=${status}`;
    console.log(params);
    return this.http.put<any>(this.reservationsUrl + '/u/' + params, this.httpOptions);
  }
}
