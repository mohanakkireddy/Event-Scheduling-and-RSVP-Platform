import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private dialog: MatDialog, private router: Router){}
  openLoginModal(): void {
    const dialogRef = this.dialog.open(LoginComponent, {
      panelClass: 'overlay', // Add the overlay class
    });
  }
  // showEvents(){
  //   this.router.navigate(['home', 'eventlist']);
  // }
}
