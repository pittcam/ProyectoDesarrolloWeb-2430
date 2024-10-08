import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { ConductorDTO } from '../dto/conductor-dto';

@Injectable({
  providedIn: 'root'
})
export class ConductorService {

  private httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
    })
  };
  constructor(private http: HttpClient) {}

  conductorList(): Observable<ConductorDTO[]> {
    return this.http.get<ConductorDTO[]>(`${environment.SERVER_URL}/conductor`);
  }

  crearConductor(conductorDTO: ConductorDTO) : Observable<ConductorDTO> {
    return this.http.post<ConductorDTO>(
      `${environment.SERVER_URL}/conductor`,
      conductorDTO,
      this.httpOptions
    )
  }

  recuperarConductorPorId(id: number): Observable<ConductorDTO> {
    return this.http.get<ConductorDTO>(`${environment.SERVER_URL}/conductor/${id}`);
  }

  buscarConductorPorNombre(nombre: string): Observable<ConductorDTO[]> {
    return this.http.get<ConductorDTO[]>(`${environment.SERVER_URL}/conductor?nombre=${nombre}`);
  }


  actualizarConductor(conductor: ConductorDTO): Observable<any> {
    return this.http.put(`${environment.SERVER_URL}/conductor/${conductor.id}`, conductor);
  }

  eliminarConductor(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.SERVER_URL}/conductor/${id}`, this.httpOptions);
  }
}
