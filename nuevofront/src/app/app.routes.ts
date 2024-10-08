import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ConductorCreateComponent } from './conductores/conductor-create/conductor-create.component';
import { ConductorListComponent } from './conductores/conductor-list/conductor-list.component';
import { ConductorViewComponent } from './conductores/conductor-view/conductor-view.component';
import { ConductorEditComponent } from './conductores/conductor-edit/conductor-edit.component';
import { BusCreateComponent } from './buses/bus-create/bus-create.component';
import { BusListComponent } from './buses/bus-list/bus-list.component';
import { BusViewComponent } from './buses/bus-view/bus-view.component';
import { BusEditComponent } from './buses/bus-edit/bus-edit.component';

export const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent }, // Ruta para el dashboard
  { path: 'crear-conductor', component: ConductorCreateComponent }, // Ruta para crear conductor
  { path: 'conductores', component: ConductorListComponent }, // Ruta para listar conductores
  { path: 'conductores/editar/:id', component: ConductorEditComponent },
  { path: 'conductores/ver/:id', component: ConductorViewComponent }, // Ruta para ver un conductor
  { path: 'crear-bus', component: BusCreateComponent },
  { path: 'buses/ver/:id', component: BusViewComponent },
  { path: 'buses/editar/:id', component: BusEditComponent },
  { path: 'buses', component: BusListComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' } // Redirección al dashboard
];
