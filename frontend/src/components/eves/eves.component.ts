import { Component } from '@angular/core';
import { EventServiceService } from '../../services/event-service.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-eves',
  standalone: true,
  imports: [NgFor],
  templateUrl: './eves.component.html',
  styleUrl: './eves.component.css'
})
export class EvesComponent {
  events: any[] =[];
  constructor(private eventService: EventServiceService){}
  ngOnInit(): void {
    this.eventService.getEvents()
    .subscribe((data) => {
      this.events = data;
    });
  } 

}
