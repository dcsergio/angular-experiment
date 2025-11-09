import { CommonModule } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';
import { RandomNumberService } from '../../services/random-number.service';

@Component({
  selector: 'app-home',
  imports: [RouterModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnDestroy {
  toggle() { this.isVisible = !this.isVisible };
  show() { this.isVisible = true };
  hide() { this.isVisible = false };
  isVisible: boolean = true;
  isActive: boolean = false;

  randomNumber: string = 'In attesa...';
  private subscription?: Subscription;

  constructor(private readonly randomService: RandomNumberService) {
    console.log('🏗️ HomeComponent COSTRUITO');
    console.log('🔍 Service disponibile?', !!this.randomService);
  }


  avvia() {
    this.subscription = this.randomService.getRandomNumber$().subscribe({
      next: (value) => {
        this.randomNumber = value;
        console.log('Numero ricevuto:', value);
      },
      error: (err) => console.error('Errore:', err)
    });
    this.isActive = true;
  }

  ngOnDestroy() {
    console.log('🗑️ Componente distrutto, unsubscribe...');
    this.subscription?.unsubscribe();
  }

  ferma() {
    console.log('Ferma il polling');
    this.subscription?.unsubscribe();
    this.isActive = false;
  }
}