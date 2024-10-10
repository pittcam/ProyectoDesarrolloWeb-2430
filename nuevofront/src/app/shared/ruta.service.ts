import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { RutaDTO } from '../dto/ruta-dto';

@Injectable({
  providedIn: 'root'
})
export class RutaService {
  private httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };
  constructor(private http: HttpClient) {}

  // Obtener todas las rutas
  obtenerRutas(): Observable<RutaDTO[]> {
    return this.http.get<RutaDTO[]>(`${environment.SERVER_URL}/rutas`);
  }

  // Eliminar una ruta por su ID
  eliminarRuta(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.SERVER_URL}/rutas/${id}`);
  }

  crearRuta(ruta: RutaDTO): Observable<RutaDTO> {
    return this.http.post<RutaDTO>(`${environment.SERVER_URL}/rutas`, ruta);
  }

  obtenerRuta(id: number): Observable<RutaDTO> {
    return this.http.get<RutaDTO>(`${environment.SERVER_URL}/rutas/${id}`);
  }

  actualizarRuta(id: number, ruta: RutaDTO): Observable<void> {
    return this.http.put<void>(`${environment.SERVER_URL}/rutas/${id}`, ruta);
  }
}
