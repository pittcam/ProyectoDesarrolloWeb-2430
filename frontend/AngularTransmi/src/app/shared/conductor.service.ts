import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConductorDTO } from '../dto/conductor-dto';
import { HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ConductorService {

  private httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
    })
  };

  constructor(private http: HttpClient) { }

  // Obtener lista de todos los conductores
  conductorList(): Observable<ConductorDTO[]> {
    return this.http.get<ConductorDTO[]>(`${environment.SERVER_URL}/conductor`);
  }

  // Buscar conductores por nombre
  buscarPorNombre(nombre: string): Observable<ConductorDTO[]> {
    return this.http.get<ConductorDTO[]>(`${environment.SERVER_URL}/search?searchText=${nombre}`);
  }

  // Eliminar conductor por ID
  eliminarConductor(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.SERVER_URL}/${id}`);
  }
}
