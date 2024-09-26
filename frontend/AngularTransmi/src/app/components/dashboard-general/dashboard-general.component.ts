import { Component } from '@angular/core';

interface Conductor {
  nombre: string;
  cedula: string;
  telefono: string;
  direccion: string;
}

@Component({
  selector: 'app-dashboard-general',
  standalone: true,
  imports: [],
  templateUrl: './dashboard-general.component.html',
  styleUrl: './dashboard-general.component.css'
})
export class DashboardGeneralComponent {
  conductores: Conductor[] = [
    {
      nombre: 'Conductor 1',
      cedula: '123456',
      telefono: '1234567890',
      direccion: 'Dirección 1'
    },
    {
      nombre: 'Conductor 2',
      cedula: '789012',
      telefono: '0987654321',
      direccion: 'Dirección 2'
    },
    // ...
  ];

}
