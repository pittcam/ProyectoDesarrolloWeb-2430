import { Component, OnInit } from '@angular/core';
import { ConductorDTO } from '../../dto/conductor-dto';
import { catchError, Observable, of } from 'rxjs';
import { FormsModule } from '@angular/forms'; // Importa FormsModule
import { ConductorService } from '../../shared/conductor.service';
import { Router } from '@angular/router';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { HttpClientModule } from '@angular/common/http'; // Importa HttpClientModule


interface Conductor {
  id: number;
  nombre: string;
  cedula: string;
  telefono: string;
  direccion: string;
}

@Component({
  selector: 'app-dashboard-general',
  standalone: true,
  imports: [NgFor, AsyncPipe, NgIf,FormsModule,HttpClientModule],
  templateUrl: './dashboard-general.component.html',
  styleUrls: ['./dashboard-general.component.css'],
})
export class DashboardGeneralComponent implements OnInit {
  allConductores$!: Observable<ConductorDTO[]>;
  errorMessage: string = '';
  conductores: Conductor[] = [];
  searchText: string = ''; // Campo para el texto de búsqueda

  constructor(
    private conductorService: ConductorService,
    private router: Router
  ) {}

  // Cargar lista de conductores al inicializar el componente
  ngOnInit() {
    this.cargarConductores();
  }

  // Método para cargar todos los conductores desde el backend
  cargarConductores() {
    this.conductorService.conductorList().subscribe({
      next: (conductoresDTO) => {
        // Convertir ConductorDTO[] a Conductor[]
        this.conductores = conductoresDTO.map((dto) => ({
          id: dto.id ?? 0, // Si 'id' es null, lo asignamos a 0
          nombre: dto.nombre,
          cedula: dto.cedula,
          telefono: dto.telefono,
          direccion: dto.direccion,
        }));
      },
      error: (error) => {
        console.error('Error al cargar conductores:', error);
        this.errorMessage = 'Error al cargar la lista de conductores.';
      },
    });
  }

  // Método para buscar un conductor
  buscarConductor() {
    if (this.searchText.trim() === '') {
      // Si no hay texto de búsqueda, cargar todos los conductores
      this.cargarConductores();
    } else {
      this.conductorService.buscarPorNombre(this.searchText).subscribe({
        next: (conductoresDTO) => {
          // Convertir ConductorDTO[] a Conductor[]
          this.conductores = conductoresDTO.map((dto) => ({
            id: dto.id ?? 0,
            nombre: dto.nombre,
            cedula: dto.cedula,
            telefono: dto.telefono,
            direccion: dto.direccion,
          }));
        },
        error: (error) => {
          console.error('Error al buscar conductores:', error);
          this.errorMessage = 'No se encontraron conductores con ese nombre.';
        },
      });
    }
  }

  // Navegar al formulario de añadir conductor
  abrirAgregarConductor() {
    this.router.navigate(['/form/add/conductor']);
  }

  // Navegar a la vista para ver buses de un conductor
  abrirVerBuses(id: number) {
    this.router.navigate(['/conductor/view', id]);
  }

  // Navegar a la vista para ver detalles de un conductor
  abrirVerConductor(id: number) {
    this.router.navigate(['/conductor/detail', id]);
  }

  // Navegar al formulario de edición de conductor
  abrirEditarConductor(id: number) {
    this.router.navigate(['/form/edit/conductor', id]);
  }

  // Eliminar un conductor
  eliminarConductor(id: number) {
    if (confirm('¿Estás seguro de que quieres eliminar este conductor?')) {
      this.conductorService.eliminarConductor(id).subscribe({
        next: () => {
          this.cargarConductores(); // Recargar la lista después de eliminar
        },
        error: (error) => {
          console.error('Error al eliminar el conductor:', error);
          this.errorMessage = 'No se pudo eliminar el conductor.';
        },
      });
    }
  }
}
