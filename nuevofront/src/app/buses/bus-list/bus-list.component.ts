import { Component, OnInit } from '@angular/core';
import { catchError, Observable, of } from 'rxjs';
import { Router } from '@angular/router';
import { BusService } from '../../shared/bus.service'; // Asegúrate de que la ruta sea correcta
import { BusDTO } from '../../dto/bus-dto'; // Ajusta la ruta según sea necesario
import { AsyncPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-bus-list',
  standalone: true,
  imports: [NgFor, AsyncPipe, NgIf],
  templateUrl: './bus-list.component.html',
  styleUrls: ['./bus-list.component.css']
})
export class BusListComponent implements OnInit {
  allBuses$!: Observable<BusDTO[]>;
  errorMessage: string = '';

  constructor(private busService: BusService, private router: Router) {}

   ngOnInit(): void {
       this.allBuses$ = this.busService.listarBuses()
         .pipe(
           catchError(error => {
             console.log("Hubo un error");
             this.errorMessage = "Hubo un error";
             return of([]);
           })
         );
     }

  crearBus(): void {
    this.router.navigate(['/crear-bus']); // Navega al formulario de crear bus
  }
}
