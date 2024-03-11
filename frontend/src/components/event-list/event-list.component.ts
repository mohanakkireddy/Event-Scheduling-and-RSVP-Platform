import { Component, OnInit } from '@angular/core';
import { AgGridAngular } from 'ag-grid-angular';
import { EventServiceService } from '../../services/event-service.service';
import { EventListDTO } from './eventListDTO';
import { ColDef } from 'ag-grid-community';

@Component({
  selector: 'app-event-list',
  standalone: true,
  imports: [AgGridAngular],
  templateUrl: './event-list.component.html',
  styleUrl: './event-list.component.css'
})
export class EventListComponent implements OnInit{
  events: EventListDTO[] = [];
  columnDefs: ColDef[] = [
    { headerName: 'Event ID', field: 'eventId' },
    { headerName: 'Event Name', field: 'eventName' },
    { headerName: 'Event Date', field: 'eventDate' },
    { headerName: 'Event Time', field: 'eventTime' },
    { headerName: 'Host Name', field: 'hostName' },
    { headerName: 'Location', field: 'location' },
  ];

  constructor(private eventService: EventServiceService){}
  ngOnInit(): void {
    this.eventService.getEvents().subscribe((data) => {
      this.events = data;
    });
    
  } 

}
