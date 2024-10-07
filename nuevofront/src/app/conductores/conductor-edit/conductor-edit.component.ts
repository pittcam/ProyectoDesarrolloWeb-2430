import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; // Importa ActivatedRoute para obtener parámetros de la ruta
import { FormsModule } from '@angular/forms';
import { ConductorDTO } from '../../dto/conductor-dto';
import { ConductorService } from '../../shared/conductor.service';

@Component({
  selector: 'app-conductor-edit',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './conductor-edit.component.html',
  styleUrls: ['./conductor-edit.component.css'],
})
export class ConductorEditComponent implements OnInit { // Implementa OnInit
  conductorDTO: ConductorDTO; // Inicializa el conductorDTO
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

  actualizarConductor() {
    this.conductorService.actualizarConductor(this.conductorDTO).subscribe({
      next: (data) => {
        console.log(data);
        this.router.navigate(['/conductores']); // Redirige a la lista de conductores
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  verConductores() {
    this.router.navigate(['/conductores']); // Redirige a la lista de conductores
  }
}
