import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ConductorCreateComponent } from './conductores/conductor-create/conductor-create.component';
import { ConductorListComponent } from './conductores/conductor-list/conductor-list.component';


export const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent }, // Ruta para el dashboard
  { path: 'crear-conductor', component: ConductorCreateComponent },
  { path: 'conductores', component: ConductorListComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' }
];
