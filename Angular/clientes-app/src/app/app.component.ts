import { Component } from '@angular/core';
import { HeaderComponent } from './header/header.component';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [HeaderComponent]
})
export class AppComponent {
  title = 'Bienvenido a Angular';
  curso: string = 'Curso Spring 5 con Angular';
  profesor: string = 'Andrés Guzmán';
}
