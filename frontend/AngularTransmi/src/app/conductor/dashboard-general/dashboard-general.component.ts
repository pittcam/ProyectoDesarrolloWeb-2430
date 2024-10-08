import {Component, OnInit} from '@angular/core';
import { ConductorDTO } from '../../dto/conductor-dto';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { catchError, Observable, of } from 'rxjs';
import { ConductorService } from '../../shared/conductor.service';
import { RouterModule,Router } from '@angular/router';
import {FormsModule} from "@angular/forms";


@Component({
  selector: 'app-dashboard-general',
  standalone: true,
  imports: [NgFor, AsyncPipe, NgIf, RouterModule, FormsModule],
  templateUrl: './dashboard-general.component.html',
  styleUrl: './dashboard-general.component.css'
})
export class DashboardGeneralComponent implements OnInit {

  conductores$!: Observable<ConductorDTO[]>;
  errorMessage: string = '';
  nombreBuscado: string = '';

  constructor(private conductorService: ConductorService,private router: Router) {};

  ngOnInit() {
    // Cargar la lista inicial de conductores
    this.cargarListaConductores();
  }

  // Metodo para cargar la lista de conductores
  cargarListaConductores() {
    this.conductores$ = this.conductorService.conductorList().pipe(
      catchError(error => {
        console.error('Hubo un error al cargar la lista de conductores', error);
        this.errorMessage = 'Hubo un error al cargar la lista de conductores.';
        return of([]);
      })
    );
  }

  // Metodo para buscar conductor por nombre
  buscarConductor() {
    if (this.nombreBuscado.trim() !== '') {
      this.conductores$ = this.conductorService.buscarConductorPorNombre(this.nombreBuscado).pipe(
        catchError(error => {
          console.error('Hubo un error en la búsqueda', error);
          this.errorMessage = 'No se encontraron conductores con ese nombre.';
          return of([]);
        })
      );
    } else {
      // Si no hay nombre, recargar la lista completa
      this.cargarListaConductores();
    }
  }


  // Metodo para abrir el formulario de agregar un conductor
  abrirAgregarConductor() {
    this.router.navigate(['/conductor/add']);
  }

  // Metodo para eliminar un conductor
  eliminarConductor(id: number) {
    if (confirm('¿Estás seguro de que quieres eliminar este conductor?')) {
      this.conductorService.eliminarConductor(id).subscribe({
        next: () => {
          console.log('Conductor eliminado exitosamente');
          this.cargarListaConductores(); // Recarga la lista de conductores después de la eliminación
        },
        error: (err) => {
          console.error('Error al eliminar el conductor:', err);
          this.errorMessage = 'Hubo un error al eliminar el conductor.';
        }
      });
    }
  }

  // Métodos para la navegación
  abrirConductorView() {
    this.router.navigate(['/conductor/view/:id']);
  }

  abrirFormEditConductor() {
    this.router.navigate(['/conductor/edit/:id']);
  }

}
