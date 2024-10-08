import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrarseComponent } from './components/registrarse/registrarse.component';
import { DashboardGeneralComponent } from './conductor/dashboard-general/dashboard-general.component';
import { ConductorViewComponent } from './conductor/conductor-view/conductor-view.component';
import { FormEditConductorComponent } from './conductor/form-edit-conductor/form-edit-conductor.component';
import { FormAddConductorComponent } from './conductor/form-add-conductor/form-add-conductor.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'registrarse', component: RegistrarseComponent },
    { path: 'dashboardGeneral', component: DashboardGeneralComponent},
    { path: 'conductor/view/:id', component: ConductorViewComponent },
    { path: 'conductor/edit/:id', component: FormEditConductorComponent },
    { path: 'conductor/add', component: FormAddConductorComponent },

    { path: '**', redirectTo: '/dashboardGeneral' }  // Manejo de rutas no encontradas
];
