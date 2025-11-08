import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-home',
  imports: [RouterModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  toggle() { this.isVisible = !this.isVisible };
  show() {this.isVisible = true};
  hide() {this.isVisible = false};
  isVisible: boolean = true;

}
