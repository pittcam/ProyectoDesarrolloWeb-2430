import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ConductorService } from '../../shared/conductor.service';
import { ConductorDTO } from '../../dto/conductor-dto';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-form-edit-conductor',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './form-edit-conductor.component.html',
  styleUrls: ['./form-edit-conductor.component.css']
})
export class FormEditConductorComponent implements OnInit {

  conductorDTO: ConductorDTO = new ConductorDTO(null, '', '', '', ''); // Inicialización del objeto
  error: any;

  constructor(
    private conductorService: ConductorService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.params['id']; // Obtener el ID del conductor de la URL
    this.conductorService.recuperarConductorPorId(id).subscribe({
      next: (data) => {
        this.conductorDTO = data; // Cargar los datos del conductor recuperado en conductorDTO
        console.log('Conductor cargado:', this.conductorDTO);
      },
      error: (error) => {
        console.log('Error al cargar conductor:', error);
        this.error = 'Error al cargar los datos del conductor.';
      },
    });
  }

  onSend(form: NgForm) {
    if (form.valid) {
      this.conductorService.actualizarConductor(this.conductorDTO).subscribe({
        next: (data) => {
          console.log('Conductor actualizado:', data);
          this.router.navigate(['/dashboardGeneral']); // Redirigir a la lista de conductores
        },
        error: (error) => {
          console.log('Error al actualizar conductor:', error);
          this.error = 'Error al actualizar el conductor.';
        },
      });
    }
  }
}
