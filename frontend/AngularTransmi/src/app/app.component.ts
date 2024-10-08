import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from "./components/menu/menu.component";
import { RegistrarseComponent } from './components/registrarse/registrarse.component';
import { DashboardGeneralComponent } from './conductor/dashboard-general/dashboard-general.component';
import { MenuGeneralComponent } from './components/menu-general/menu-general.component';
import {CommonModule} from '@angular/common';
import { ConductorViewComponent } from './conductor/conductor-view/conductor-view.component';
import { FormEditConductorComponent } from './conductor/form-edit-conductor/form-edit-conductor.component';
import { FormAddConductorComponent } from './conductor/form-add-conductor/form-add-conductor.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, MenuComponent,
    RegistrarseComponent, DashboardGeneralComponent,
    MenuGeneralComponent,CommonModule,ConductorViewComponent,
    FormAddConductorComponent, FormEditConductorComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'AngularTransmilenio';
  showMenuGeneral: boolean = false;

  constructor(private router: Router) {
    // Cada vez que cambie la ruta, evaluamos si estamos en las rutas de login o registrarse
    this.router.events.subscribe(() => {
      const currentRoute = this.router.url;
      // Mostrar el menú general solo si NO estamos en login o registrarse
      this.showMenuGeneral = !['/login', '/registrarse'].includes(currentRoute);
    });
  }
}
