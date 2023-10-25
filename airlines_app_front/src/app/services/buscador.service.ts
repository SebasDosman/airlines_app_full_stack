import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { VueloCompleto } from '../interfaces/Vuelo';

@Injectable({
  providedIn: 'root'
})
export class BuscadorService {

  constructor() { }

  private vuelosBuscados$ = new Subject<VueloCompleto[]>()



  setVuelosBuscados( vuelos: VueloCompleto[] ){
    this.vuelosBuscados$.next( vuelos )
  }


  getVuelosBuscados$(): Observable<VueloCompleto[]>{
    return this.vuelosBuscados$.asObservable()
  }
}
