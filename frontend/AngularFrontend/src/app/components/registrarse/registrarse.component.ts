import { Component} from '@angular/core';
import { FormsModule } from "@angular/forms";
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-registrarse',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './registrarse.component.html',
  styleUrl: './registrarse.component.css'
})
export class RegistrarseComponent {
  onSend() {
    console.log("Formulario enviado");
  }

  nombre?: String ;
  cedula?: String;
  correo?: String ;  
  usuario?: String;
  contrasena?: String;

  constructor() { }

}
