import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { catchError, Observable, of } from 'rxjs';
import { ConductorService } from '../../shared/conductor.service';
import { ConductorDTO } from '../../dto/conductor-dto';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-conductor-list',
  standalone: true,
  imports: [NgFor, AsyncPipe, NgIf],
  templateUrl: './conductor-list.component.html',
  styleUrls: ['./conductor-list.component.css'],
})
export class ConductorListComponent implements OnInit {
  allConductors$!: Observable<ConductorDTO[]>;
  errorMessage: string = '';

  constructor(private conductorService: ConductorService) {}

  ngOnInit(): void {
    this.allConductors$ = this.conductorService.listarConductores()
      .pipe(
              catchError(
                error => {
                  console.log("Hubo un error");
                  this.errorMessage = "Hubo un error";
                  return of([]);
                }

              )
            )
          ;
        }
}
