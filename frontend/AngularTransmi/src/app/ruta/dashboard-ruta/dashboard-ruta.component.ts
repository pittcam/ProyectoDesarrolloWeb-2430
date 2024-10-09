import { Component } from '@angular/core';
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-dashboard-ruta',
  standalone: true,
    imports: [
        AsyncPipe,
        FormsModule,
        NgForOf,
        NgIf
    ],
  templateUrl: './dashboard-ruta.component.html',
  styleUrl: './dashboard-ruta.component.css'
})
export class DashboardRutaComponent {

}
