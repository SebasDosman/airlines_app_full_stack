import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PanelComponent } from './panel/panel.component';
import { AnadirAeropuertoComponent } from './anadir-aeropuerto/anadir-aeropuerto.component';
import { AnadirAvionComponent } from './anadir-avion/anadir-avion.component';
import { CrearVueloComponent } from './crear-vuelo/crear-vuelo.component';
import { EditarVueloComponent } from './editar-vuelo/editar-vuelo.component';
import { UsuariosActivosComponent } from './usuarios-activos/usuarios-activos.component';
import { EditarVueloIdComponent } from './editar-vuelo-id/editar-vuelo-id.component';

const routes: Routes = [
  { path: 'panel'                     , component: PanelComponent                 },
  { path: 'anadir-aeropuerto'         , component: AnadirAeropuertoComponent      },
  { path: 'anadir-avion'              , component: AnadirAvionComponent           },
  { path: 'crear-vuelo'               , component: CrearVueloComponent            },
  { path: 'seleccion-vuelo-a-editar'  , component: EditarVueloComponent            },
  // { path: 'editar-vuelo/:id'          , component: EditarVueloIdComponent         },
  { path: 'usuarios-activos'          , component: UsuariosActivosComponent       },
  { path: '**'                        , redirectTo: 'panel'                }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministratorRoutingModule { }
