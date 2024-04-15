import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EventServiceService } from '../../services/event-service.service';
import { EventComponent } from './event.component';
import { of } from 'rxjs';
import { FormsModule, ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';


describe('EventComponent', () => {
  let component: EventComponent;
  let fixture: ComponentFixture<EventComponent>;
  let eventServiceServiceStub: Partial<EventServiceService>;

  beforeEach(async () => {
    eventServiceServiceStub = {
      createEvent: jasmine.createSpy('createEvent').and.returnValue(of({})),
      // You can add more methods or mock responses as needed
    };
    await TestBed.configureTestingModule({
      imports: [EventComponent, ReactiveFormsModule, FormsModule],
      providers: [
        FormBuilder,
        { provide: EventServiceService, useValue: eventServiceServiceStub },
        { provide: Router, useClass: Router }, // You might want to provide a mock for Router if needed
      ],    
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should call createEvent on form submission', () => {
    // Mock form data
    const formData = {
      eventName: 'Test Event',
      eventDate: '2024-03-15',
      eventTime: '12:00 PM',
      hostName: 'Test Host',
      location: 'Test Location',
    };

    // Set form values
    component.createForm.patchValue(formData);

    // Trigger form submission
    component.create();

    // Check that createEvent was called with the correct data
    expect(eventServiceServiceStub.createEvent).toHaveBeenCalledWith(formData);

    // You can add more expectations based on your component's behavior
  });
});
