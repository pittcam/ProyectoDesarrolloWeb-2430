import { Component } from '@angular/core';
import {FormsModule, NgForm} from '@angular/forms';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-form-edit-conductor',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './form-edit-conductor.component.html',
  styleUrls: ['./form-edit-conductor.component.css']
})
export class FormEditConductorComponent {

  // Propiedades para enlazar con los inputs del formulario
  nombre: string = '';
  cedula: string = '';
  telefono: string = '';
  direccion: string = '';

  constructor(private router: Router) {}

  // Metodo que se ejecutará al enviar el formulario
  onSend(form: NgForm) {
    if (form.valid) {
      // Aquí puedes procesar los datos del formulario. Ejemplo:
      const conductorData = {
        nombre: this.nombre,
        cedula: this.cedula,
        telefono: this.telefono,
        direccion: this.direccion
      };

      console.log('Datos del conductor:', conductorData);

      // Después de guardar o procesar los datos, podrías redirigir al dashboard:
      this.router.navigate(['/dashboard/general']);
    } else {
      console.log('Formulario inválido.');
    }
  }
}
