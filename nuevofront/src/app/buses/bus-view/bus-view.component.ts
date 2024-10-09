import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BusService } from '../../shared/bus.service';
import { BusDTO } from '../../dto/bus-dto';
import { catchError, of, Observable } from 'rxjs';
import { AsyncPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-bus-view',
  standalone: true,
  imports: [NgIf, AsyncPipe],
  templateUrl: './bus-view.component.html',
  styleUrls: ['./bus-view.component.css'],
})
export class BusViewComponent implements OnInit {
  bus$!: Observable<BusDTO | null>;
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private busService: BusService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.bus$ = this.busService.recuperarBusPorId(id)
      .pipe(
        catchError(error => {
          this.errorMessage = 'Error al cargar los datos del bus';
          return of(null);
        })
      );
  }
}
