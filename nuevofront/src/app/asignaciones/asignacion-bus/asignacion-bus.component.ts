import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BusService } from '../../shared/bus.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AsignacionService } from '../../shared/asignacion.service';
import { AsignacionDTO } from '../../dto/asignacion-dto';

@Component({
  selector: 'app-asignacion-bus',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './asignacion-bus.component.html',
  styleUrls: ['./asignacion-bus.component.css'],
})
export class AsignacionBusComponent implements OnInit {
  buses: any[] = [];
  selectedBusId: number | null = null;
  conductorId!: number;
  mensaje: string | null = null;

  constructor(
    private busService: BusService,
    private asignacionService: AsignacionService,
    private router: Router
  ) {}

  ngOnInit() {
    this.cargarBusesDisponibles();
  }

  cargarBusesDisponibles() {
    this.busService.getBusesDisponibles().subscribe(
      (buses) => {
        if (buses.length === 0) {
          this.mensaje = "No hay buses disponibles para asignar.";
        } else {
          this.buses = buses;
        }
      },
      (error) => {
        console.error('Error al cargar buses:', error);
        this.mensaje = "Hubo un error al cargar los buses. Intenta nuevamente.";
      }
    );
  }

  asignarBus() {
    if (this.selectedBusId && this.conductorId) {
      const asignacion: AsignacionDTO = {
        busId: this.selectedBusId,
        conductorId: this.conductorId,
        // rutaId y horarioId se pueden agregar si tienes valores para ellos
      };

      this.asignacionService.asignarBus(asignacion).subscribe(
        () => {
          this.mensaje = "Bus asignado exitosamente.";
          this.router.navigate(['/conductores']);
        },
        (error) => {
          this.mensaje = error.error?.message || "Error al asignar el bus. Intenta nuevamente.";
          console.error('Error al asignar el bus:', error);
        }
      );
    } else {
      this.mensaje = "Por favor, selecciona un bus y proporciona el ID del conductor.";
      console.error('No se ha seleccionado ningún bus o no se ha proporcionado el ID del conductor.');
    }
  }
}
