import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Factura, Reserva, ReservaCompleta } from '../interfaces/Reserva';
import { environment } from 'src/environments/environment.development';
import { Observable, Subject, Subscription } from 'rxjs';
import { Asiento, Trayecto, Vuelo } from '../interfaces/Vuelo';
import { Aeropuerto, Avion } from '../interfaces/Aeropuerto';
import { AeropuertosService } from './aeropuertos.service';
import { VuelosService } from './vuelos.service';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  private idAsiento$ = new Subject<string>();

  private reservaCompletaUsuario$ = new Subject<ReservaCompleta>();

  urlUsuario: string = `${ environment.serverUrl }reserva/`
  urlAsiento: string = `${ environment.serverUrl }asiento/`
  urlReserva: string = `${ environment.serverUrl}reserva/` 
  urlFactura: string = `${ environment.serverUrl}factura/`


  constructor(  private http      : HttpClient,
                private aeroServ  : AeropuertosService,
                private vueloServ : VuelosService ) { }


  cambiarIdAsiento( id: string  ){
    this.idAsiento$.next( id )
  }

  getIdAsiento$(): Observable<string> {
    return this.idAsiento$.asObservable();
  }

  cambiarReservaCompletaUsuario( reservaCompleta: ReservaCompleta ){
    this.reservaCompletaUsuario$.next( reservaCompleta )
  }

  getReservaCompletaUsuario(): Observable<ReservaCompleta>{
    return this.reservaCompletaUsuario$.asObservable()
  }

  postReserva( reserva: Reserva ): Observable<Reserva>{
    return this.http.post<Reserva>(`${ this.urlReserva }guardar-reserva`, reserva )
  }

  postAsiento( asiento: Asiento ): Observable<Asiento>{
    return this.http.post<Asiento>(`${this.urlAsiento}guardar-asiento`, asiento )
  }


  postFactura( factura: Factura ): Observable<Factura>{
    return this.http.post<Factura>(`${ this.urlFactura }guardar-factura`, factura )
  }

  obtenerReservasVuelo( idVuelo: number | undefined ): Observable<Reserva[]> {
      return this.http.get<Reserva[]>(`${ this.urlReserva }obtener-reservasVuelo/${ idVuelo }`)
  }

  getAsiento( idAsiento: number  | undefined ): Observable<Asiento>{
    return this.http.get<Asiento>(`${ this.urlAsiento }obtener-asiento/${idAsiento}`)
  }


  obtenerReservasPorUsuario( cedula: string ): Observable<Reserva[]>{
    return this.http.get<Reserva[]>(`${ this.urlReserva }obtener-reservasUsuario/${ cedula}`)
  }

  getReservaById( idReserva: string ): Observable<Reserva>{
    return this.http.get<Reserva>(`${ this.urlReserva}obtener-reserva/${ idReserva }`)
  }

  actualizarReserva( reserva: Reserva ): Observable<Reserva>{
    
    return this.http.put<Reserva>(`${ this.urlReserva }actualizar-reserva`, reserva )
  }


  
  getInformacionCompletaReserva( idReserva: string ){
    
    let avionesEnv      : Avion[]          = []
    let escalasEnv      : Trayecto[]       = []
    let aeropuertosEnv  : Aeropuerto[]     = []
    let reservaEnv      : Reserva
    let vueloEnv        : Vuelo
    let asientoEnv      : Asiento
    let reservaCompleta!: ReservaCompleta
    
    this.getReservaById( idReserva ).subscribe( reserva =>{
      reservaEnv = reserva

      this.vueloServ.getVueloById( reserva.idVuelo + "").subscribe( vuelo =>{
        vueloEnv = vuelo

        this.getAsiento( reserva.idAsiento ).subscribe( asiento =>{
          asientoEnv = asiento

          this.vueloServ.obtenerTrayectosByIdVuelo( vuelo.idVuelo +"" ).then( (trayectos: Trayecto[]) =>{
            escalasEnv = trayectos
  
            for( let i = 0; i < trayectos.length; i ++ ){
  
              this.aeroServ.getAeropuertoById( trayectos[i].idAeropuertoOrigen + "").subscribe( aeropuerto1 =>{
                aeropuertosEnv.push( aeropuerto1 )
  
                this.aeroServ.getAeropuertoById( trayectos[i].idAeropuertoDestino + "").subscribe( aeropuerto2 =>{
                  aeropuertosEnv.push( aeropuerto2 )
                  
                  this.aeroServ.getAvionById( trayectos[i].idAvion + "").subscribe( avion =>{
                    avionesEnv.push( avion )
  
  
                    if( i == ( trayectos.length - 1 )){
                      
                      reservaCompleta = { avionesEnv, escalasEnv, aeropuertosEnv, reservaEnv, vueloEnv, asientoEnv }
                      this.cambiarReservaCompletaUsuario( reservaCompleta )
                    }
                  })
                })
              })
            }
          })
        })
      })
    })
  }
}
