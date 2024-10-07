import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConductorService } from '../../shared/conductor.service';
import { ConductorDTO } from '../../dto/conductor-dto';
import { catchError, of, Observable } from 'rxjs';
import { AsyncPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-conductor-view',
  standalone: true,
  imports: [NgIf, AsyncPipe],
  templateUrl: './conductor-view.component.html',
  styleUrls: ['./conductor-view.component.css'],
})
export class ConductorViewComponent implements OnInit {
  conductor$!: Observable<ConductorDTO | null>; // Permitir null en el observable
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private conductorService: ConductorService
  ) {}

  ngOnInit(): void {
    // Obtener el id desde la ruta
    const id = Number(this.route.snapshot.paramMap.get('id'));

    // Recuperar el conductor por ID y manejar posibles errores
    this.conductor$ = this.conductorService.recuperarConductorPorId(id)
      .pipe(
        catchError(error => {
          this.errorMessage = 'Error al cargar los datos del conductor';
          return of(null);  // Devolvemos un observable nulo en caso de error
        })
      );
  }
}
