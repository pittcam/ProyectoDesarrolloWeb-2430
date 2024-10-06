import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { ConductorDTO } from '../dto/conductor-dto'; // Asegúrate de que esta ruta sea correcta

@Injectable({
  providedIn: 'root', // Asegúrate de que el servicio esté disponible en toda la aplicación
})
export class ConductorService {
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpClient) {}

  listarConductores(): Observable<ConductorDTO[]> {
    return this.http.get<ConductorDTO[]>(`${environment.SERVER_URL}/conductores`, this.httpOptions);
  }

  crearConductor(conductorDTO: ConductorDTO): Observable<ConductorDTO> {
    return this.http.post<ConductorDTO>(
      `${environment.SERVER_URL}/conductores`,
      conductorDTO,
      this.httpOptions
    );
  }

  // Otras funciones como buscarConductores, etc.
}
