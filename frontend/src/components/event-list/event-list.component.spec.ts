import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EventListComponent } from './event-list.component';
import { EventServiceService } from '../../services/event-service.service';
import { Subject, of } from 'rxjs';

describe('EventListComponent', () => {
  let component: EventListComponent;
  let fixture: ComponentFixture<EventListComponent>;
  let eventServiceServiceStub: jasmine.SpyObj<EventServiceService>;

  beforeEach(() => {
    eventServiceServiceStub = jasmine.createSpyObj('EventServiceService', ['getEvents']);
    eventServiceServiceStub.getEvents.and.returnValue(of([]));

    TestBed.configureTestingModule({
      providers: [{ provide: EventServiceService, useValue: eventServiceServiceStub }],
    }).compileComponents();

    fixture = TestBed.createComponent(EventListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('should unsubscribe on ngOnDestroy', () => {
    // Arrange
    const destroy$ = component['destroy$'] as Subject<void>; // Access private member

    spyOn(destroy$, 'next');
    spyOn(destroy$, 'complete');

    // Act
    component.ngOnDestroy();

    // Assert
    expect(destroy$.next).toHaveBeenCalled();
    expect(destroy$.complete).toHaveBeenCalled();
  });

  
});
