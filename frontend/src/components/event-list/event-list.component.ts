import { Component, OnDestroy, OnInit } from '@angular/core';
import { AgGridAngular } from 'ag-grid-angular';
import { EventServiceService } from '../../services/event-service.service';
import { EventListDTO } from './eventListDTO';
import { ColDef } from 'ag-grid-community';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
@Component({
  selector: 'app-event-list',
  standalone: true,
  imports: [AgGridAngular],
  templateUrl: './event-list.component.html',
  styleUrl: './event-list.component.css'
})
export class EventListComponent implements OnInit, OnDestroy{
  events: EventListDTO[] = [];
  private destroy$ = new Subject<void>();
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
    this.eventService.getEvents()
    .pipe(takeUntil(this.destroy$))
    .subscribe((data) => {
      this.events = data;
    });
  } 
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

}
