import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ConductorService } from '../../shared/conductor.service';
import { ConductorDTO } from '../../dto/conductor-dto';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-form-edit-conductor',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './form-edit-conductor.component.html',
  styleUrls: ['./form-edit-conductor.component.css']
})
export class FormEditConductorComponent implements OnInit {

  // Propiedades para enlazar con los inputs del formulario
  nombre: string = '';
  cedula: string = '';
  telefono: string = '';
  direccion: string = '';
  conductorDTO: ConductorDTO;
  error: any;

  constructor(
    private conductorService: ConductorService,
    private router: Router,
    private route: ActivatedRoute // Para acceder a los parámetros de la ruta
  ) {
    this.conductorDTO = new ConductorDTO(null, '', '', '', '');
  }

  ngOnInit() {
    const id = this.route.snapshot.params['id']; // Obtiene el ID del conductor de la URL
    this.conductorService.recuperarConductorPorId(id).subscribe({
      next: (data) => {
        this.conductorDTO = data; // Asigna el conductor recuperado al conductorDTO
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  onSend(form:NgForm) {
    this.conductorService.actualizarConductor(this.conductorDTO).subscribe({
      next: (data) => {
        console.log(data);
        this.router.navigate(['/dashboardGeneral']); // Redirige a la lista de conductores
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

}
