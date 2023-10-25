import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Usuario } from '../interfaces/Usuarios';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private usuarioReservas$ = new Subject<Usuario>();
  urlUsuario: string = `${ environment.serverUrl }usuario/`

  constructor( private http: HttpClient ) { }


  cambiarUsuarioReserva( usuario: Usuario  ){
    this.usuarioReservas$.next( usuario )
  }

  getUsuarioReserva$(): Observable<Usuario> {
    return this.usuarioReservas$.asObservable();
  }

  obtenerUsuarios(): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(`${ this.urlUsuario }obtener-usuarios`)
  }

  obtenerUsuarioByCedula( cedula: string ): Observable<Usuario>{
    return this.http.get<Usuario>(`${ this.urlUsuario }obtener-usuarioCedula/${ cedula }`)
  }

  crearUsuario( usuario: Usuario ): Observable<Usuario>{
    return this.http.post<Usuario>(`${ this.urlUsuario }guardar-usuario`, usuario )
  }
}
