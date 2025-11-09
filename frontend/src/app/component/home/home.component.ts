import { CommonModule } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';
import { RandomNumberService } from '../../services/random-number.service';
import { ChildComponent } from "../child/child.component";

@Component({
  selector: 'app-home',
  imports: [RouterModule, CommonModule, ChildComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnDestroy {
  toggle() { this.isVisible = !this.isVisible };
  show() { this.isVisible = true };
  hide() { this.isVisible = false };
  isVisible: boolean = true;
  isActive: boolean = false;

  randomNumber: string = 'Waiting...';

  childValue: string = 'not produced yet';

  private subscription?: Subscription;

  constructor(private readonly randomService: RandomNumberService) {
    console.log('🏗️ HomeComponent BUILT');
    console.log('🔍 Is Service available?', !!this.randomService);
  }


  start() {
    this.subscription = this.randomService.getRandomNumber$().subscribe({
      next: (value) => {
        this.randomNumber = value;
        console.log('Received number:', value);
      },
      error: (err) => console.error('Error:', err)
    });
    this.isActive = true;
  }

  ngOnDestroy() {
    console.log('🗑️ On Destroy, unsubscribe...');
    this.subscription?.unsubscribe();
  }

  stop() {
    console.log('Stop polling');
    this.subscription?.unsubscribe();
    this.isActive = false;
  }

  child() {
    this.childValue = this.randomNumber;
  }
}