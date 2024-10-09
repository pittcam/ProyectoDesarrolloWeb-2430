import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {AsyncPipe, NgForOf} from "@angular/common";

@Component({
  selector: 'app-form-add-ruta',
  standalone: true,
  imports: [
    FormsModule,
    AsyncPipe,
    NgForOf
  ],
  templateUrl: './form-add-ruta.component.html',
  styleUrl: './form-add-ruta.component.css'
})
export class FormAddRutaComponent {

}
