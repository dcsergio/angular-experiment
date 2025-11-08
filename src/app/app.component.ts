import { Component } from '@angular/core';
import { RouterOutlet } from "@angular/router";
import { NavbarComponent } from "./component/navbar/navbar.component";
import { HomeComponent } from "./component/home/home.component";


@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  imports: [RouterOutlet, NavbarComponent, HomeComponent]
})
export class AppComponent {
  title: any
}
