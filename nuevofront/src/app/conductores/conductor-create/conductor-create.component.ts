import { Component } from '@angular/core';
import { ConductorDTO } from '../../dto/conductor-dto'; // Asegúrate de que la ruta sea correcta
import { ConductorService } from '../../shared/conductor.service'; // Asegúrate de que la ruta sea correcta
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-conductor-create',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './conductor-create.component.html',
  styleUrls: ['./conductor-create.component.css'],
})
export class ConductorCreateComponent {
  conductorDTO: ConductorDTO = new ConductorDTO(null, '', '', '', '');
  error: any;

  constructor(private conductorService: ConductorService) {}

  crearConductor() {
    this.conductorService.crearConductor(this.conductorDTO).subscribe({
      next: (data) => {
        console.log('Conductor creado!', data);
        // Opcional: Reiniciar el formulario después de crear el conductor
        this.reiniciarFormulario();
      },
      error: (error) => {
        console.error('Error al crear el conductor', error);
        this.error = error; // Guarda el error para mostrarlo en la plantilla si es necesario
      },
    });
  }

  reiniciarFormulario() {
    this.conductorDTO = new ConductorDTO(null, '', '', '', '');
    this.error = null; // Limpiar el error
  }
}
