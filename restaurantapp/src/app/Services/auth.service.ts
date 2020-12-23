import { Injectable } from '@angular/core';
import { Employee } from './../Models/employee';
import { LoginClass} from '../Models/loginclass';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpEvent, HttpHeaders } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})


/**
 *  Service responsible for accessing Authorization endpoint and accessing session info
 * 
 * 
 * @author Justin Kroh
 * */

export class AuthService {


/**
 * URL of API Endpoint
 * 
 * */
  private employeesUrl = 'http://localhost:8081/restaurantappserver/api/employee/login';

  constructor(private http: HttpClient) { }

  userLogin?: LoginClass;

  firstName: string = '';

  //MANAGER, EMPLOYEE
  user_type: string = '';

  isLoggedIn = false;

  // store the URL so we can redirect after logging in
  redirectUrl: string = '/Menu';

  setLogin(correctCredentials: boolean) {
   this.isLoggedIn = correctCredentials;
   console.log('set logon status ' + correctCredentials);
    
  }

  setFirstName(firstName: string){
    this.firstName = firstName;

  }

  setUserType(user_type:string){
    this.user_type = user_type;
  }

  getSessionType(){
   var type: any = sessionStorage.getItem('user_type');
   return type;
  }

  getUserName(){
    var username: any = sessionStorage.getItem('firstName');
    return username;
  }


  setSession(user_type: string, firstName: string) {
    sessionStorage.setItem('user_type', user_type);
    sessionStorage.setItem('firstName', firstName);

  }

  removeSession(){
    sessionStorage.removeItem('user_type');
    sessionStorage.removeItem('firstName');

  }


  httpOptions: any = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    observe: 'response'
  };


  login(email: string, password: string): Observable<any> {
  
    this.userLogin = new LoginClass(email, password);

    return this.http.post<any>(this.employeesUrl, this.userLogin, this.httpOptions);

  }

  logout(): void {
    this.isLoggedIn = false;
    this.removeSession();

  }
}
