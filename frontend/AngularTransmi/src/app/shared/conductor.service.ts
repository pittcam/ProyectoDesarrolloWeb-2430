import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConductorDTO } from '../dto/conductor-dto';

@Injectable({
  providedIn: 'root'
})
export class ConductorService {

  private apiUrl = '/api/conductor';  // URL base del backend

  constructor(private http: HttpClient) { }

  // Obtener lista de todos los conductores
  conductorList(): Observable<ConductorDTO[]> {
    return this.http.get<ConductorDTO[]>(`${this.apiUrl}`);
  }

  // Buscar conductores por nombre
  buscarPorNombre(nombre: string): Observable<ConductorDTO[]> {
    return this.http.get<ConductorDTO[]>(`${this.apiUrl}/search?searchText=${nombre}`);
  }

  // Eliminar conductor por ID
  eliminarConductor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
