import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EventComponent } from '../components/event/event.component';
import { HttpClientModule } from '@angular/common/http';
import { AgGridModule } from 'ag-grid-angular';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, EventComponent, HttpClientModule, RouterOutlet,AgGridModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
