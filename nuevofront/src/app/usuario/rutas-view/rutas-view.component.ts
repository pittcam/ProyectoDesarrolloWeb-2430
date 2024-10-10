import { Component } from '@angular/core';
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {catchError, Observable, of} from 'rxjs';
import {RutaDTO} from '../../dto/ruta-dto';
import {RutaService} from '../../shared/ruta.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-rutas-view',
  standalone: true,
    imports: [
        AsyncPipe,
        FormsModule,
        NgForOf,
        NgIf
    ],
  templateUrl: './rutas-view.component.html',
  styleUrl: './rutas-view.component.css'
})
export class RutasViewComponent {
  allRutas$!: Observable<RutaDTO[]>; // Observable para las rutas
  errorMessage: string = ''; // Mensaje de error
  nombreBuscado: string = '';

  constructor(private rutaService: RutaService, private router: Router) {} // Inyectar Router

  ngOnInit(): void {
    this.allRutas$ = this.rutaService.obtenerRutas()
      .pipe(
        catchError(error => {
          console.log("Hubo un error al cargar las rutas");
          this.errorMessage = "Hubo un error al cargar las rutas"; // Mensaje de error
          return of([]); // Devuelve un arreglo vacío en caso de error
        })
      );
  }

  verRuta(id: number | null): void {
    if (id !== null) {
      this.router.navigate(['/rutas/ver', id]); // Redirigir a la vista de la ruta con el id
    }
  }

  cargarListaRutas() {
    this.allRutas$ = this.rutaService.obtenerRutas().pipe(
      catchError(error => {
        console.error('Hubo un error al cargar la lista de conductores', error);
        this.errorMessage = 'Hubo un error al cargar la lista de conductores.';
        return of([]);
      })
    );
  }


  // Metodo para buscar conductor por nombre
  buscarRuta() {
    if (this.nombreBuscado.trim() !== '') {
      this.allRutas$ = this.rutaService.buscarRutaPorNombre(this.nombreBuscado).pipe(
        catchError(error => {
          console.error('Hubo un error en la búsqueda', error);
          this.errorMessage = 'No se encontraron conductores con ese nombre.';
          return of([]);
        })
      );
    } else {
      // Si no hay nombre, recargar la lista completa
      this.cargarListaRutas();
    }
  }





}
