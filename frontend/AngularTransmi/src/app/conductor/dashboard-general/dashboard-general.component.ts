import { Component } from '@angular/core';
import { ConductorDTO } from '../../dto/conductor-dto';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { catchError, Observable, of } from 'rxjs';
import { ConductorService } from '../../shared/conductor.service';

interface Conductor {
  nombre: string;
  cedula: string;
  telefono: string;
  direccion: string;
}

@Component({
  selector: 'app-dashboard-general',
  standalone: true,
  imports: [NgFor, AsyncPipe, NgIf],
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

  /*ngOnInit() {
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
  }*/
}
