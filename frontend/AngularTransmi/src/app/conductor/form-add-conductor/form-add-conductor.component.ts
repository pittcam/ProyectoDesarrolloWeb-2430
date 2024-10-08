import { Component } from '@angular/core';
import { FormsModule, NgForm } from "@angular/forms";
import { RouterLink } from "@angular/router";
import { Router } from '@angular/router';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { ConductorService } from '../../shared/conductor.service'; // Importa el servicio
import { ConductorDTO } from '../../dto/conductor-dto'; // Importa el DTO

@Component({
  selector: 'app-form-add-conductor',
  standalone: true,
  imports: [FormsModule, RouterLink, NgFor, AsyncPipe, NgIf],
  templateUrl: './form-add-conductor.component.html',
  styleUrls: ['./form-add-conductor.component.css']
})
export class FormAddConductorComponent {
  nombre: string = '';
  cedula: string = '';
  telefono: string = '';
  direccion: string = '';

  constructor(private router: Router, private conductorService: ConductorService) {} // Inyectamos el servicio

  onSend(form: NgForm) {
    if (form.valid) {
      const conductorData = new ConductorDTO(null, this.nombre, this.cedula, this.telefono, this.direccion);

      // Llamamos al servicio para crear el nuevo conductor
      this.conductorService.crearConductor(conductorData).subscribe({
        next: (response) => {
          console.log('Conductor agregado:', response);

          // Navegar al dashboard después de agregar el conductor
          this.router.navigate(['/dashboardGeneral']);
        },
        error: (error) => {
          console.error('Error al agregar el conductor:', error);
        }
      });
    } else {
      console.log('Formulario inválido');
    }
  }
}
