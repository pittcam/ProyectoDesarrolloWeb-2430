import { Routes } from '@angular/router';
import { ConductorCreateComponent } from './conductores/conductor-create/conductor-create.component';
import { ConductorListComponent } from './conductores/conductor-list/conductor-list.component';

export const routes: Routes = [
  { path: 'crear-conductor', component: ConductorCreateComponent },
  { path: 'conductores', component: ConductorListComponent },
  { path: '', redirectTo: '/crear-conductor', pathMatch: 'full' }
];
