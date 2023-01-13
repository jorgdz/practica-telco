import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './dashboard/home/home.component';
import { LoginComponent } from './security/login/login.component';
import { AuthGuard } from './shared/guards/auth.guard';
import { NoauthGuard } from './shared/guards/noauth.guard';

const routes: Routes = [
  {
    path: 'signin',
    component: LoginComponent,
    canActivate: [NoauthGuard],
  },
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
