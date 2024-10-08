import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { RouterLink } from "@angular/router";
import { Router } from '@angular/router';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-form-add-conductor',
  standalone: true,
  imports: [FormsModule,RouterLink, NgFor, AsyncPipe, NgIf],
  templateUrl: './form-add-conductor.component.html',
  styleUrl: './form-add-conductor.component.css'
})
export class FormAddConductorComponent {
  onSend() {
    this.router.navigate(['/dashboard/general']);
  }

  constructor(private router: Router) {}


}
