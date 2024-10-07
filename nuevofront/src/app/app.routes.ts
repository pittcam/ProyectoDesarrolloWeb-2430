import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ConductorCreateComponent } from './conductores/conductor-create/conductor-create.component';
import { ConductorListComponent } from './conductores/conductor-list/conductor-list.component';
import { ConductorViewComponent } from './conductores/conductor-view/conductor-view.component';
import { ConductorEditComponent } from './conductores/conductor-edit/conductor-edit.component';

export const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent }, // Ruta para el dashboard
  { path: 'crear-conductor', component: ConductorCreateComponent }, // Ruta para crear conductor
  { path: 'conductores', component: ConductorListComponent }, // Ruta para listar conductores
  { path: 'conductores/editar/:id', component: ConductorEditComponent },
  { path: 'conductores/ver/:id', component: ConductorViewComponent }, // Ruta para ver un conductor
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' } // Redirección al dashboard
];
