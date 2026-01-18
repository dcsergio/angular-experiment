// random-number.service.ts
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, timer} from 'rxjs';
import {map, switchMap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RandomNumberService {
  private readonly apiUrl = 'http://localhost:4040/numbers/random?min=0&max=1';
  private readonly intervalMs = 1000;

  startTimestamp: number;

  constructor(private readonly http: HttpClient) {
    this.startTimestamp = Date.now();
  }

  getRandomNumber$(): Observable<string[]> {
    return timer(0, this.intervalMs).pipe(
      switchMap((c) => {
        // 2. this.http.get() returns a Observable<string>
        return this.http.get(this.apiUrl, {responseType: 'text'}).pipe(
          // 3. map: manipulates the value (response) emitted from HTTP Observable
          map((response) => {
            // 'response' NOW is the string returned by the server
            let n: number = +response; // Convert string to number
            n = Math.floor(n * 100); // multiply by 100 and removes decimal part
            let s: string = n.toString();
            console.log('value of n: ' + s);
            // 4. Emit the result
            return [s, c.toString()];
          })
        );
      })
    );
  }
}
