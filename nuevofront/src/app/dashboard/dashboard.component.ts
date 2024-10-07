import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterModule], // Necesario para usar [routerLink]
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'], // Asegúrate de que el nombre esté correcto
})
export class DashboardComponent {
  constructor(private router: Router) {}

  abrirCrearConductor() {
    this.router.navigate(['/crear-conductor']); // Navega a la pantalla de crear conductor
  }
}
