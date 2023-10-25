import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';

import { Vuelo, Trayecto, VueloCompleto } from '../interfaces/Vuelo'    ;
import { HttpClient }                     from '@angular/common/http'   ;
import { Observable }                     from 'rxjs'                   ;
import { AeropuertosService }             from './aeropuertos.service'  ;





@Injectable({
  providedIn: 'root'
})
export class VuelosService {


  constructor(  private http: HttpClient,
                private AeroServ: AeropuertosService ) { }

  urlVuelos   = `${ environment.serverUrl }vuelo`
  urlTrayecto = `${ environment.serverUrl}trayecto`



  postVuelo( vuelo: Vuelo ): Observable<Vuelo> | null{
    try{  
      return this.http.post<Vuelo>( `${ this.urlVuelos}/guardar-vuelo`,  vuelo )    
    } catch( err ){

      console.log( "Error creando vuelo -> ", err )
      return null
    }
  }


  postTrayecto( trayecto: Trayecto): Observable<Trayecto> | null{
    try{
      return this.http.post<Trayecto>(  `${ this.urlTrayecto}/guardar-trayecto`, trayecto  )
    }catch( err ){
      console.log("Error creando reserva -> ", err )
      return null
    }
  }


  getVuelos(): Observable<Vuelo[]> | null {
    try{

      return this.http.get<Vuelo[]>(`${ this.urlVuelos }/obtener-vuelos`)
    } catch( err ){
      console.log( "Error obteniendo vuelos -> ", err )
      return null
    }
  }


  getVueloById( idVuelo: string ):Observable<Vuelo>{
    return this.http.get<Vuelo>(`${ this.urlVuelos }/obtener-vuelo/${ idVuelo }`)
  }

  obtenerTrayectos(): Observable<Trayecto[]>{
    return this.http.get<Trayecto[]>(`${ this.urlTrayecto}/obtener-trayectos`)
  }


  obtenerTrayectosByIdVuelo( idVuelo: string ): Promise<any[]>{

    let trayectos: any[] = []
    return new Promise<any[]>( ( resolve, reject ) =>{

      try {

        this.obtenerTrayectos().subscribe( ( res: Trayecto[]) =>{
          trayectos = res.filter( trayecto => ( trayecto.idVuelo == parseInt( idVuelo ) && trayecto.estado ==="A") )
          resolve( trayectos )
        })
      } catch (error) {
          console.log( "Error obteniendo los trayectos ->", error)
          reject( [])
      }
    })
  }



  obtenerVueloConTodaLaData(): Promise<any[]>{
    
      let vuelosCompleto: any[]  = []
      return new Promise<any[]>( ( resolve, reject ) => {

        try{

          this.getVuelos()?.subscribe( (res: Vuelo[]) =>{
          
          
            vuelosCompleto = res.map( vueloAntiguo  =>{
              
              let vueloNuevo: VueloCompleto = {
                aeropuertoOrigen    : { nombre: '', iata: '', estado: '', ubicacion : ''},
                aeropuertoDestino   : { nombre: '', iata: '', estado: '', ubicacion : ''},
                precio              : 0,
                horaLlegada         : new Date(),
                horaSalida          : new Date(),
                precioAsientoBasico : 0,
                precioAsientoVip    : 0,
                precioAsientoNormal : 0,
                estado              : ''
              };
              
              if( vueloAntiguo.estado == "A"){
    
               
                
                vueloNuevo.idVuelo      = vueloAntiguo.idVuelo
                vueloNuevo.horaSalida   = vueloAntiguo.horaSalida
                vueloNuevo.horaLlegada  = vueloAntiguo.horaLlegada
    
                vueloNuevo.precioAsientoBasico = vueloAntiguo.precioAsientoBasico
                vueloNuevo.precioAsientoNormal = vueloAntiguo.precioAsientoNormal
                vueloNuevo.precioAsientoVip    = vueloAntiguo.precioAsientoVip
                vueloNuevo.estado = "A"
                vueloNuevo.precio = vueloAntiguo.precio
                
              
                this.AeroServ.getAeropuertoById( vueloAntiguo.idAeropuertoOrigen + "")  .subscribe( res => { 
                  vueloNuevo.aeropuertoOrigen = res 
                
                  this.AeroServ.getAeropuertoById( vueloAntiguo.idAeropuertoDestino + "") .subscribe( res => { 
                    vueloNuevo.aeropuertoDestino = res
                  })
                })
              }

              return vueloNuevo
            })
            
            resolve( vuelosCompleto )
          })
        } catch( err ){
          console.log("Error obteniendo vuelos completos ->" + err )
          reject([])
        }
      })
  }
}
