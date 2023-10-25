import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path        : 'usuario',
    loadChildren: () => import('./user/user.module').then( m  => m.UserModule)  
  },
  {
    path        : 'administrador',
    loadChildren: () => import('./administrator/administrator.module').then( m  => m.AdministratorModule),
    canActivate : [ AuthGuard ]
  },
  {
    path        : 'auth',
    loadChildren: () => import('./auth/auth.module').then( m => m.AuthModule )
  },
  {
    path      : '**'      ,
    redirectTo: 'usuario'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
