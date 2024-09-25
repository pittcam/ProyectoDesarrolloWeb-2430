import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from "./components/menu/menu.component";
import { RegistrarseComponent } from './components/registrarse/registrarse.component';
import { DashboardGeneralComponent } from './components/dashboard-general/dashboard-general.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, MenuComponent,RegistrarseComponent, DashboardGeneralComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'AngularTransmilenio';
}