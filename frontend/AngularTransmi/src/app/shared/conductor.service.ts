import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
  /*constructor(private http: HttpClient) {}

  conductorList(): Observable<PersonDTO[]> {
    return this.http.get<PersonDTO[]>(`${environment.SERVER_URL}/person`);
  }

  crearPersona(personDTO: PersonDTO) : Observable<PersonDTO> {
    return this.http.post<PersonDTO>(
      `${environment.SERVER_URL}/person`,
      personDTO,
      this.httpOptions
    )
  }*/
}
