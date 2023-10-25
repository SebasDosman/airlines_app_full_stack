
import { NgModule }     from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule }                from './user-routing.module';
import { MainPageComponent }                from './main-page/main-page.component';
import { ObservarReservasComponent }        from './observar-reservas/observar-reservas.component';
import { PasarelaPagosComponent }           from './pasarela-pagos/pasarela-pagos.component';
import { RealizarReservaComponent }         from './realizar-reserva/realizar-reserva.component';
import { SeleccionarVueloComponent }        from './seleccionar-vuelo/seleccionar-vuelo.component';
import { VuelosPagadosComponent }           from './vuelos-pagados/vuelos-pagados.component';
import { ListaVuelosComponent }             from './components/lista-vuelos/lista-vuelos.component';
import { BuscarVuelosComponent }            from './components/buscar-vuelos/buscar-vuelos.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InfoVueloComponent }               from './components/info-vuelo/info-vuelo.component';
import { DatosViajerosComponent }           from './components/datos-viajeros/datos-viajeros.component';
import { SeleccionAsientosComponent } from './components/seleccion-asientos/seleccion-asientos.component';
import { InfoReservaComponent } from './components/info-reserva/info-reserva.component';
import { ToastrModule } from 'ngx-toastr';


@NgModule({
  declarations: [
    MainPageComponent         ,
    ObservarReservasComponent ,
    PasarelaPagosComponent    ,
    RealizarReservaComponent  ,
    SeleccionarVueloComponent ,
    VuelosPagadosComponent    ,
    ListaVuelosComponent      ,
    BuscarVuelosComponent     ,
    InfoVueloComponent        ,
    DatosViajerosComponent,
    SeleccionAsientosComponent,
    InfoReservaComponent
  ],
  imports: [
    CommonModule      ,
    UserRoutingModule ,
    ReactiveFormsModule,
    FormsModule,
    ToastrModule.forRoot()
  ]
})
export class UserModule { }
