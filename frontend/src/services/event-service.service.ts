import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EventDto } from '../components/event/eventDTO';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { NewUser } from '../components/login/newUser';


@Injectable({
  providedIn: 'root'
})
export class EventServiceService {
  private eventUrl = 'http://localhost:8090/api';
  private authUrl = 'http://localhost:8080/api';


  constructor(private http: HttpClient) { }
  createEvent(eventDto: EventDto): Observable<any> {
    return this.http.post(`${this.eventUrl}/event/create`, eventDto);
  }
  getEvents(): Observable<any[]> {
    return this.http.get<any[]>(`${this.eventUrl}/event/view`)
  }

  loginUser(email:string, password: string): Observable<any> {
    return this.http.post(`${this.authUrl}/user/login`,{email, password});
  }

  signUpUser(newUser: NewUser):Observable<any>{
    return this.http.post(`${this.authUrl}/user/register`,newUser);
  }
  


}
