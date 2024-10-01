import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface Conductor {
  nombre: string;
  cedula: string;
  telefono: string;
  direccion: string;
}

@Component({
  selector: 'app-dashboard-general',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard-general.component.html',
  styleUrl: './dashboard-general.component.css'
})
export class DashboardGeneralComponent {
  errorMessage: string = '';
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
  constructor(private conductorService: ConductorService) {};

  ngOnInit() {
    this.conductorService.conductorList()
      .pipe(
        catchError(
          error => {
            console.error('Hubo un error');
            this.errorMessage = "Hubo un error.";
            return of([]);
          }
        )
      )
    
    ;
  }
}
