import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from "@angular/forms";
import {Router, ActivatedRoute, RouterLink} from '@angular/router';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { ConductorService } from '../../shared/conductor.service';
import { ConductorDTO } from '../../dto/conductor-dto';

@Component({
  selector: 'app-form-add-conductor',
  standalone: true,
  imports: [FormsModule, NgFor, AsyncPipe, NgIf, RouterLink],
  templateUrl: './form-add-conductor.component.html',
  styleUrls: ['./form-add-conductor.component.css']
})
export class FormAddConductorComponent implements OnInit {
  // Propiedades para enlazar con el formulario
  conductorId: number | null = null; // Almacenar el id del conductor a editar
  nombre: string = '';
  cedula: string = '';
  telefono: string = '';
  direccion: string = '';

  constructor(
    private conductorService: ConductorService,
    private router: Router,
    private route: ActivatedRoute  // Para obtener parámetros de la URL
  ) {}

  ngOnInit(): void {
    // Verificar si estamos en modo de edición
    const idFromRoute = this.route.snapshot.paramMap.get('id');
    this.conductorId = idFromRoute ? Number(idFromRoute) : null;

    if (this.conductorId) {
      this.conductorService.recuperarConductorPorId(this.conductorId).subscribe({
        next: (conductor) => {
          this.nombre = conductor.nombre;
          this.cedula = conductor.cedula;
          this.telefono = conductor.telefono;
          this.direccion = conductor.direccion;
        },
        error: (error) => {
          console.error('Error al cargar el conductor:', error);
        }
      });
    }
  }


  // Metodo que se ejecutará al enviar el formulario
  onSend(form: NgForm) {
    if (form.valid) {
      const conductorData = new ConductorDTO(this.conductorId, this.nombre, this.cedula, this.telefono, this.direccion);

      if (this.conductorId) {
        // Si tenemos un id, actualizar el conductor existente
        this.conductorService.actualizarConductor(conductorData).subscribe({
          next: (response) => {
            console.log('Conductor actualizado:', response);

            // Navegar al dashboard después de actualizar
            this.router.navigate(['/dashboard-general']);
          },
          error: (error) => {
            console.error('Error al actualizar el conductor:', error);
          }
        });
      } else {
        console.log('No se proporcionó un id válido para actualizar');
      }
    } else {
      console.log('Formulario inválido');
    }
  }
}
