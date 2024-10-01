import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-menu-general',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './menu-general.component.html',
  styleUrl: './menu-general.component.css'
})
export class MenuGeneralComponent {
  selectedOption: string = '';

  // Método para seleccionar una opción del menú
  selectOption(option: string) {
    this.selectedOption = option;
  }
}
