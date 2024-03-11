import { Routes } from '@angular/router';
import { EventComponent } from '../components/event/event.component';
import { EventListComponent } from '../components/event-list/event-list.component';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: EventComponent },
  { path: 'eventlist', component: EventListComponent }
];
