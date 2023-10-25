import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from './main-page/main-page.component';
import { ObservarReservasComponent } from './observar-reservas/observar-reservas.component';
import { PasarelaPagosComponent } from './pasarela-pagos/pasarela-pagos.component';
import { RealizarReservaComponent } from './realizar-reserva/realizar-reserva.component';
import { SeleccionarVueloComponent } from './seleccionar-vuelo/seleccionar-vuelo.component';
import { VuelosPagadosComponent } from './vuelos-pagados/vuelos-pagados.component';
import { InfoReservaComponent } from './components/info-reserva/info-reserva.component';

const routes: Routes = [
  { path: 'principal'             , component :  MainPageComponent          },
  { path: 'observar-reservas'     , component :  ObservarReservasComponent  },
  { path: 'observar-reservas/:id' , component :  InfoReservaComponent  },
  { path: 'pasarela-pagos'        , component :  PasarelaPagosComponent     },
  { path: 'realizar-reserva/:id'  , component :  RealizarReservaComponent   },
  { path: 'seleccionar-vuelo'     , component :  SeleccionarVueloComponent  },
  { path: 'vuelos-pagados'        , component :  VuelosPagadosComponent     },
  { path: '**'                    , redirectTo: 'principal'                 }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class UserRoutingModule { }
