import { Component } from '@angular/core';
import { NavbarComponent } from "./components/navbar/navbar.component";
import { RouterModule, RouterOutlet } from "@angular/router";


@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [NavbarComponent, RouterModule, RouterOutlet]
})
export class AppComponent {
  title: string = 'ang-app'
}
