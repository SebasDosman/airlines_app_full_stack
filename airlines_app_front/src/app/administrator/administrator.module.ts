import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdministratorRoutingModule } from './administrator-routing.module';
import { PanelComponent } from './panel/panel.component';
import { CrearVueloComponent } from './crear-vuelo/crear-vuelo.component';
import { EditarVueloComponent } from './editar-vuelo/editar-vuelo.component';
import { AnadirAvionComponent } from './anadir-avion/anadir-avion.component';
import { AnadirAeropuertoComponent } from './anadir-aeropuerto/anadir-aeropuerto.component';
import { UsuariosActivosComponent } from './usuarios-activos/usuarios-activos.component';
import { RouterModule } from '@angular/router';
import { EditarVueloIdComponent } from './editar-vuelo-id/editar-vuelo-id.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoadingComponent } from '../components/loading/loading.component';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    PanelComponent,
    CrearVueloComponent,
    EditarVueloComponent,
    AnadirAvionComponent,
    AnadirAeropuertoComponent,
    UsuariosActivosComponent,
    EditarVueloIdComponent,
    LoadingComponent
  ],
  imports: [
    CommonModule,
    AdministratorRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    ToastrModule.forRoot()
  ]
})
export class AdministratorModule { }
