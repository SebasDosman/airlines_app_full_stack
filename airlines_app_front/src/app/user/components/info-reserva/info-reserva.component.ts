import { Component, Input } from '@angular/core';
import {  Router } from '@angular/router';
import { Reserva, ReservaCompleta } from 'src/app/interfaces/Reserva';
import { ReservaService } from 'src/app/services/reserva.service';
import { ListaAsientos } from '../../../interfaces/Vuelo';

@Component({
  selector: 'app-info-reserva',
  templateUrl: './info-reserva.component.html',
  styleUrls: ['./info-reserva.component.css']
})
export class InfoReservaComponent {

  infoReserva!: ReservaCompleta
  listaAeropuertos: any = []
  constructor(  private reservaServ: ReservaService,
                private router: Router ){}


  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.reservaServ.getInformacionCompletaReserva( this.router.url.split("/").pop() + "" ) 
    this.reservaServ.getReservaCompletaUsuario().subscribe( res => {
      this.infoReserva = res 
      this.matrizAeropuertos()
    })
  }


  cancelarReserva(){
    this.infoReserva.reservaEnv.estado = "I"
    this.infoReserva.reservaEnv.estadoPago = "I"
    console.log( this.infoReserva )
    this.reservaServ.actualizarReserva( this.infoReserva.reservaEnv ).subscribe( reserva =>{

      console.log( this.infoReserva.reservaEnv )
      this.router.navigate(['/usuario/observar-reservas'])
    })
  }

  pagarReserva(){
    this.infoReserva.reservaEnv.estado      = "A"
    this.infoReserva.reservaEnv.estadoPago  = "A"
    console.log( this.infoReserva )
    this.reservaServ.actualizarReserva( this.infoReserva.reservaEnv ).subscribe( reserva =>{

      console.log( this.infoReserva.reservaEnv )
      this.router.navigate(['/usuario/observar-reservas'])
    })
  }

  matrizAeropuertos(){

    const escalas = this.infoReserva.escalasEnv

    escalas.sort((a, b) => {
      const horaLlegadaA = new Date(a.horaLlegada).getTime();
      const horaLlegadaB = new Date(b.horaLlegada).getTime();
    
      if (horaLlegadaA < horaLlegadaB) {
        return -1;
      } else if (horaLlegadaA > horaLlegadaB) {
        return 1;
      } else {
        return 0;
      }
    });
  

    for( let i = 0; i < escalas.length; i ++ ){
      let aeropuertos = []

      for( let k = 0; k < this.infoReserva.aeropuertosEnv.length; k ++ ){
        if( escalas[i].idAeropuertoOrigen   == this.infoReserva.aeropuertosEnv[ k ].idAeropuerto ) aeropuertos[0] = ( this.infoReserva.aeropuertosEnv[k] )
        if( escalas[i].idAeropuertoDestino  == this.infoReserva.aeropuertosEnv[ k ].idAeropuerto ) aeropuertos[1] = ( this.infoReserva.aeropuertosEnv[k] )
        
      }
      this.listaAeropuertos.push( aeropuertos )
    }
  }
}
