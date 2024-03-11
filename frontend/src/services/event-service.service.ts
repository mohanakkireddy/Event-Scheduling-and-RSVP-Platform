import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EventDto } from '../components/event/eventDTO';
import { Observable } from 'rxjs';
import { HttpClientModule } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class EventServiceService {
  private baseUrl = 'http://localhost:8080/api/event';

  constructor(private http: HttpClient) { }
  createEvent(eventDto: EventDto): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, eventDto);
  }
  getEvents(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/view`);
  }


}
