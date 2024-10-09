import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {AsyncPipe, NgForOf} from "@angular/common";

@Component({
  selector: 'app-form-edit-ruta',
  standalone: true,
  imports: [
    FormsModule,
    AsyncPipe,
    NgForOf
  ],
  templateUrl: './form-edit-ruta.component.html',
  styleUrl: './form-edit-ruta.component.css'
})
export class FormEditRutaComponent {

}
