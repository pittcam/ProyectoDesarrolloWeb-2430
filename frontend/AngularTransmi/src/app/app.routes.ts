import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrarseComponent } from './components/registrarse/registrarse.component';
import { DashboardGeneralComponent } from './conductor/dashboard-general/dashboard-general.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'registrarse', component: RegistrarseComponent },
    { path: 'dashboardGeneral', component: DashboardGeneralComponent},


    { path: '**', redirectTo: '/dashboardGeneral' }  // Manejo de rutas no encontradas
];