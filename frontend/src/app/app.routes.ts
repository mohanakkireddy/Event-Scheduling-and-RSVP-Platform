import { Routes } from '@angular/router';
import { EventComponent } from '../components/event/event.component';
import { EventListComponent } from '../components/event-list/event-list.component';
import { HomeComponent } from '../components/home/home.component';
import { LoginComponent } from '../components/login/login.component';
import { EvesComponent } from '../components/eves/eves.component';

export const routes: Routes = [
    // { path: '', redirectTo: '/eventlist', pathMatch: 'full' },
    // { path: '', component: HomeComponent,
    //   children:[
    //     { path: 'eventlist', component: EventListComponent },
    //   ]
    // }
    { path: 'eventlist', component: EvesComponent }
];
