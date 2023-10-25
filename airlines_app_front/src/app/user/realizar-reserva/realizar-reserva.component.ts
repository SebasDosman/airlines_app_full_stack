import { Component } from '@angular/core';

import { Router } from '@angular/router';
import { Asiento, Vuelo } from 'src/app/interfaces/Vuelo';
import { VuelosService } from '../../services/vuelos.service';
import { Trayecto } from '../../interfaces/Vuelo';
import { Subject, Subscription } from 'rxjs';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ReservaService } from 'src/app/services/reserva.service';
import { Usuario } from 'src/app/interfaces/Usuarios';
import { Factura, Reserva } from 'src/app/interfaces/Reserva';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-realizar-reserva',
  templateUrl: './realizar-reserva.component.html',
  styleUrls: ['./realizar-reserva.component.css']
})

export class RealizarReservaComponent {
  idVuelo    : string = ""
  vuelo     !: Vuelo
  trayectos !: Trayecto[]
  usuario   !: Usuario
  ubiAsiento  : string = ""
  precioTotal: number = 0

  isProcessingRequest: boolean = false
  clientesSubscription!: Subscription;
  reservaSubscription !: Subscription;
  UsuarioSubscription !: Subscription;
  reserva:Reserva = {
    idVuelo     : 0,
    idAsiento   : 0,
    idUsuario   : 0,
    precioTotal : 0,
    estadoPago  : '',
    fecha       : new Date().toISOString().substring(0, 16),
    estado      : 'A'
  }


  constructor(  private router      : Router,
                private vueloServ   : VuelosService,
                private usuarioServ : UsuarioService,
                private reservaServ : ReservaService,
                private toastr  : ToastrService ){}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.

    this.idVuelo = this.router.url.split("/")[3]
    this.clientesSubscription = this.vueloServ.getVueloById(this.idVuelo).subscribe( res =>{
      this.vuelo = res

      this.vueloServ.obtenerTrayectosByIdVuelo( this.idVuelo ).then( response =>{
        this.trayectos = response
      })
    })


    this.UsuarioSubscription = this.usuarioServ.getUsuarioReserva$().subscribe( res => this.usuario = res )

    this.reservaSubscription = this.reservaServ.getIdAsiento$().subscribe( res => {

      this.ubiAsiento = res
      this.obtenerPrecioTotal()
    })
  }


  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
     this.UsuarioSubscription.unsubscribe()
     this.clientesSubscription.unsubscribe()
     this.reservaSubscription.unsubscribe()
  }


  obtenerPrecioTotal(): number{
    //El return de este metodo es para no utilizar la logica de las condiciones 2 veces
    if( this.ubiAsiento[0] == "v"){

      this.precioTotal = this.vuelo.precioAsientoVip + this.vuelo.precio
      return 1
    }
    else if( this.ubiAsiento[0] == "e"){
      this.precioTotal = this.vuelo.precioAsientoBasico + this.vuelo.precio
      return 3
    }
    else if( this.ubiAsiento[0] == "n"){
      this.precioTotal = this.vuelo.precioAsientoNormal + this.vuelo.precio
      return 2
    }

  return 0
  }


  actualizarInformacionTrayecto( estadoPago: string ){
    // this.reserva.idAsiento   = this.idAsiento
    //TODO primero enviar el asiento y obtener el id
    this.reserva.idVuelo     = this.vuelo.idVuelo
    this.reserva.idUsuario   = this.usuario.idUsuario
    this.reserva.precioTotal = this.precioTotal
    this.reserva.estadoPago  = estadoPago
  }



  crearAsiento(){

    const asiento: Asiento = {
      idTipoAsiento : this.obtenerPrecioTotal(),
      ubicacion     : this.ubiAsiento,
      estado: 'A'
    }

    
    this.reservaServ.postAsiento( asiento ).subscribe( res => {
      console.log( this.reserva )
      this.reserva.idAsiento = res.idAsiento
      console.log( this.reserva )
      this.reservaServ.postReserva( this.reserva ).subscribe(
      res =>{
      
      console.log( res )
      this.toastr.success('Reserva realizada con exito', 'Éxito')

      if( res.estadoPago == "A"){
        this.generarFactura( res )
      }
      },
      err => {
        let errorMessage = "Error al crear la reserva";

        if (err.error && err.error.mensaje) {
          errorMessage = err.error.mensaje;
        }

        this.toastr.error(errorMessage, "Error");
      })

    })
  }


  generarFactura( reserva: Reserva | undefined ){
    const factura: Factura = {

      idReserva: reserva?.idReserva ,
      fecha: reserva?.fecha,
      estado: "A"
    }

    this.reservaServ.postFactura( factura ).subscribe( res => console.log( res ))
  }

  pagarReserva(){

    this.actualizarInformacionTrayecto( "A")
    this.crearAsiento()

    this.isProcessingRequest = false
  }

  guardarReserva(){

    this.actualizarInformacionTrayecto( "I")
    this.crearAsiento()
    this.isProcessingRequest = false
  }
}
