// random-number.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, timer } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { response } from 'express';

@Injectable({
  providedIn: 'root'
})
export class RandomNumberService {
  private readonly apiUrl = 'http://localhost:4040/numbers/random?min=30&max=40';
  private readonly intervalMs = 5000;

  constructor(private http: HttpClient) { }


  getRandomNumber$(): Observable<string> {
    return timer(0, this.intervalMs).pipe(
      switchMap((x) => {
        return this.http.get(this.apiUrl, { responseType: 'text' });
      }

      )
    );
  }

  /**
   * Alternativa: singola chiamata HTTP senza ripetizione
   */
  getRandomNumberOnce(): Observable<string> {
    return this.http.get(this.apiUrl, { responseType: 'text' });
  }
}