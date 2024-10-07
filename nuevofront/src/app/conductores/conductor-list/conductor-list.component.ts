import { Component, OnInit } from '@angular/core';
import { catchError, Observable, of } from 'rxjs';
import { Router } from '@angular/router'; // Importar Router
import { ConductorService } from '../../shared/conductor.service';
import { ConductorDTO } from '../../dto/conductor-dto';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-conductor-list',
  standalone: true,
  imports: [NgFor, AsyncPipe, NgIf],
  templateUrl: './conductor-list.component.html',
  styleUrls: ['./conductor-list.component.css'],
})
export class ConductorListComponent implements OnInit {
  allConductors$!: Observable<ConductorDTO[]>;
  errorMessage: string = '';

  constructor(private conductorService: ConductorService, private router: Router) {} // Inyectar Router

  ngOnInit(): void {
    this.allConductors$ = this.conductorService.listarConductores()
      .pipe(
        catchError(error => {
          console.log("Hubo un error");
          this.errorMessage = "Hubo un error";
          return of([]);
        })
      );
  }

  verConductor(id: number | null): void {
    if (id !== null) {
      this.router.navigate(['/conductores/ver', id]); // Navegar usando id
    }
  }
    eliminarConductor(id: number | null): void {
      if (id !== null) {
        const confirmDelete = confirm('¿Estás seguro de que deseas eliminar este conductor?');
        if (confirmDelete) {
          this.conductorService.eliminarConductor(id).subscribe({
            next: () => {
              console.log('Conductor eliminado con éxito');
              this.ngOnInit(); // Volver a cargar la lista después de eliminar
            },
            error: (error: any) => {
              console.log(error);
              this.errorMessage = "Error al eliminar el conductor.";
            },
          });
        }
      }
    }

  editarConductor(id: number): void {
    this.router.navigate(['/conductores/editar', id]);
  }
}
