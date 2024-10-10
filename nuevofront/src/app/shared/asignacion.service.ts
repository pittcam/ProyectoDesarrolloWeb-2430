import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { AsignacionDTO } from '../dto/asignacion-dto';
import { RutaDTO } from '../dto/ruta-dto'; // Asegúrate de importar RutaDTO

@Injectable({
  providedIn: 'root'
})
export class AsignacionService {
  private httpOptions = {
      headers: new HttpHeaders({
          'Content-Type': 'application/json',
      }),
  };

  private apiUrl = 'http://localhost:8088/asignacion'; // Cambia esto según tu configuración

  constructor(private http: HttpClient) { }

  asignarBus(asignacion: AsignacionDTO): Observable<AsignacionDTO> {
    return this.http.post<AsignacionDTO>(this.apiUrl, asignacion);
  }

  // Método para obtener rutas asignadas a un bus
  obtenerRutasPorBus(busId: number): Observable<RutaDTO[]> {
    return this.http.get<RutaDTO[]>(`${this.apiUrl}/rutas/${busId}`, this.httpOptions);
  }

  // Asignar una ruta a un bus
  asignarRuta(asignacion: AsignacionDTO): Observable<AsignacionDTO> {
    return this.http.post<AsignacionDTO>(`${environment.SERVER_URL}/asignaciones`, asignacion, this.httpOptions);
  }

}
