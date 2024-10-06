import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { ConductorListComponent } from "./conductores/conductor-list/conductor-list.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'nuevofront';
}
