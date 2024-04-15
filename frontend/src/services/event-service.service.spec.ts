import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { EventServiceService } from './event-service.service';
import { HttpClient } from '@angular/common/http';

describe('EventServiceService', () => {
  let service: EventServiceService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;
  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get', 'post']);
    
    TestBed.configureTestingModule({
      providers:[{provide: HttpClient, useValue: httpClientSpy}]
    });
    service = TestBed.inject(EventServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy(); 
  });
  
});
