import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/Services/auth.service';


@Injectable({
  providedIn: 'root'
})


/**
 * The authguard holds the canActivate function, which checks the auth service to see if a user is logged in
 * by checking if there is data in session storage
 * 
 * 
 * 
 * @author Justin Kroh
 * */
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log('AuthGuard#canActivate called');
    return this.checkLogin();


  }


  /**
 * Checks the auth service to see if a session type exists, if not routes to the employee login page
 * 
 * @author Justin Kroh
 * */
  checkLogin(): boolean | UrlTree{

    if(this.authService.getSessionType()) {

      return true;

    }
    else{
      return this.router.parseUrl('/Login');
    }
    
    
  



  }
  
}
