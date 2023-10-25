import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ubicaciones } from '../interfaces/Ubicaciones';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UbicacionesService {

  constructor( private http: HttpClient) { }

  paisesUrl: string = `https://countriesnow.space/api/v0.1/countries`

  getPaises(): Observable<Ubicaciones>{

    return this.http.get<Ubicaciones>( this.paisesUrl )
  }

}
