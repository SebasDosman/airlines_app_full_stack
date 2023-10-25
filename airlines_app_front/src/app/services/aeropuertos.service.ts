import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { Aeropuerto, Avion } from '../interfaces/Aeropuerto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AeropuertosService {

  constructor( private http: HttpClient ) { }

  urlAeropuertos = `${ environment.serverUrl }aeropuerto`
  urlAviones     = `${ environment.serverUrl}avion`

  getAeropuertos(): Observable<Aeropuerto[]>{
    return this.http.get<Aeropuerto[]>(`${ this.urlAeropuertos}/obtener-aeropuertos`)
  }

  postAeropuertos( aeropuerto: Aeropuerto ): Observable<Aeropuerto> | null{
    try{
      return this.http.post<Aeropuerto>( `${ this.urlAeropuertos}/guardar-aeropuerto`,  aeropuerto )    
    } catch( err ){

      console.log( "Error creando aeropuerto -> ", err )
      return null
    }
  }

  getAeropuertoById( idAeropuerto: string ): Observable<Aeropuerto>{
    return this.http.get<Aeropuerto>(`${ this.urlAeropuertos }/obtener-aeropuerto/${ idAeropuerto }`)
  }

  getAvionById( idAvion: string ): Observable<Avion>{
    return this.http.get<Avion>(`${ this.urlAviones }/obtener-avion/${ idAvion }`)
  }

  getAviones(): Observable<Avion[]>{
    return this.http.get<Avion[]>(`${ this.urlAviones}/obtener-aviones`)
  }

  postAvion( avion: Avion ): Observable<Avion> | null{
    try {
      
      return this.http.post<Avion>(`${this.urlAviones}/guardar-avion`, avion )
    } catch (err) {
      console.log("Error agregando avion -> ", err )
      return null
    }
  }
  
}
