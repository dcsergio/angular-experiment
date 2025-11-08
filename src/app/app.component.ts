import {Component} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AComponent} from './a/a.component';
import {BComponent} from './b/b.component';

const routes: Routes = [
  {path: 'a', component: AComponent},
  {path: 'b', component: BComponent},
  {path: '', redirectTo: 'a', pathMatch: 'full'}
];

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule],
  template: `
    <header>
      <nav>
        <a routerLink="/a">A</a>
        <br>
        <a routerLink="/b">B</a>
      </nav>
    </header>
    <main>
      <router-outlet></router-outlet>
    </main>
    <footer>
      © 2025 Il Mio Sito Angular
    </footer>
  `
})
export class AppComponent {
}
