import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConductorService } from '../../shared/conductor.service';
import { ConductorDTO } from '../../dto/conductor-dto';

@Component({
  selector: 'app-conductor-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './conductor-list.component.html',
  styleUrls: ['./conductor-list.component.css'],
})
export class ConductorListComponent implements OnInit {
  conductores: ConductorDTO[] = [];
  errorMessage: string | null = null;

  constructor(private conductorService: ConductorService) {}

  ngOnInit(): void {
    this.obtenerConductores(); // Llama al método para obtener los conductores al iniciar el componente
  }

  obtenerConductores(): void {
    this.conductorService.listarConductores().subscribe({
      next: (conductores: ConductorDTO[]) => {
        this.conductores = conductores;
        this.errorMessage = null; // Limpia el mensaje de error si la carga es exitosa
        console.log('Conductores cargados:', this.conductores); // Para depuración
      },
      error: (error: any) => {
        this.errorMessage = 'Error al obtener la lista de conductores';
        console.error('Error: ', error); // Muestra el error en la consola
      },
    });
  }
}
