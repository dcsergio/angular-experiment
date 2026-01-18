import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { FormsComponent } from './components/forms/forms.component';
import { PipesComponent } from './components/pipes/pipes.component';
import { VouchersComponent } from './components/vouchers/vouchers.component';
import { VerifyComponent } from './components/verify/verify.component';



export const routes: Routes = [

  {
    path: 'home',
    component: HomeComponent,
    title: 'Bindings e Servizi'
  },
  {
    path: 'forms',
    component: FormsComponent,
    title: 'Gestione dei Form'
  },
  {
    path: 'pipes',
    component: PipesComponent,
    title: 'Angular Pipes'
  },
  {
    path: 'vouchers',
    component: VouchersComponent,
    title: 'Genera Voucher'
  },
  {
    path: 'verify',
    component: VerifyComponent,
    title: 'Verifica Voucher'
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/home'
  }
];