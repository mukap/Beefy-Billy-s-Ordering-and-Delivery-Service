import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})


/**
 * Display Navbar
 * 
 * @author Justin Kroh, Ronald Martz
 * */
export class NavbarComponent implements OnInit {


  constructor(public authService: AuthService) {
   }

  ngOnInit(): void {
  }

  logout():void{
    this.authService.logout();
  }

}
