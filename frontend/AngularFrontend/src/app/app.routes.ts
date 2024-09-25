import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrarseComponent } from './components/registrarse/registrarse.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'registrarse', component: RegistrarseComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' },  // Redirigir al login por defecto
    { path: '**', redirectTo: '/login' }  // Manejo de rutas no encontradas
];
