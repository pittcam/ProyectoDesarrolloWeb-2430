import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-form-edit-conductor',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './form-edit-conductor.component.html',
  styleUrl: './form-edit-conductor.component.css'
})

export class FormEditConductorComponent {

  constructor(private router: Router) {
  }



}
