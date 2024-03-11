import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgIf } from '@angular/common';
import { EventDto } from './eventDTO';
import { EventServiceService } from '../../services/event-service.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-event',
  standalone: true,
  imports: [ReactiveFormsModule,FormsModule, NgIf],
  templateUrl: './event.component.html',
  styleUrl: './event.component.css'
})
export class EventComponent implements OnInit{
  createForm!: FormGroup;
  get eventName(){
    return this.createForm.get('eventName')!;
  }
  get eventDate(){
    return this.createForm.get('eventDate')!;
  }
  get eventTime(){
    return this.createForm.get('eventTime')!;
  }
  get hostName(){
    return this.createForm.get('hostName')!;
  }
  get location(){
    return this.createForm.get('location')!;
  }
  constructor(private formBuilder: FormBuilder, private eventService: EventServiceService, private router: Router){}

  ngOnInit(): void {

    this.createForm = this.formBuilder.group({
      eventName: ['', Validators.required],
      eventDate: ['', Validators.required],
      eventTime: ['', Validators.required],
      hostName: ['', Validators.required],
      location: ['', Validators.required],

    });
    
  }
  create(){
    if (this.createForm.valid) {
      const eventDto: EventDto = {
        eventName: this.createForm.get('eventName')?.value,
        eventDate: this.createForm.get('eventDate')?.value,
        eventTime: this.createForm.get('eventTime')?.value,
        hostName: this.createForm.get('hostName')?.value,
        location: this.createForm.get('location')?.value,
      };
      this.eventService.createEvent(eventDto)
        .subscribe(
          (response) => {
            console.log('Event successfully created:', response);
            this.router.navigate(['/eventlist']);
          },
          (error) => {
            console.error('Error creating event:', error);
          }
        );
      console.log('Event Details:', eventDto);
    } else {
      console.log('Form is invalid. Please check for errors.');
      this.createForm.markAllAsTouched();
    }
  }

}
