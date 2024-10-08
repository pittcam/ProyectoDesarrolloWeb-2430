import { Component } from '@angular/core';
import { FormsModule, NgForm } from "@angular/forms";
import { RouterLink } from "@angular/router";
import { Router } from '@angular/router';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-form-add-conductor',
  standalone: true,
  imports: [FormsModule, RouterLink, NgFor, AsyncPipe, NgIf],
  templateUrl: './form-add-conductor.component.html',
  styleUrls: ['./form-add-conductor.component.css']
})
export class FormAddConductorComponent {
  // Propiedades para enlazar con el formulario
  nombre: string = '';
  cedula: string = '';
  telefono: string = '';
  direccion: string = '';

  constructor(private router: Router) {}

  // Método que se ejecutará al enviar el formulario
  onSend(form: NgForm) {
    if (form.valid) {
      // Procesar los datos del formulario
      const conductorData = {
        nombre: this.nombre,
        cedula: this.cedula,
        telefono: this.telefono,
        direccion: this.direccion
      };

      console.log('Datos del conductor:', conductorData);

      // Navegar al dashboard después de procesar los datos
      this.router.navigate(['/dashboard-general']);
    } else {
      console.log('Formulario inválido');
    }
  }
}
