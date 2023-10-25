import { Component, Input } from '@angular/core';
import {  Vuelo, VueloCompleto } from '../../../interfaces/Vuelo';
import { Validators } from '@angular/forms';
import { BuscadorService } from '../../../services/buscador.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-lista-vuelos',
  templateUrl: './lista-vuelos.component.html',
  styleUrls: ['./lista-vuelos.component.css']
})
export class ListaVuelosComponent {
  @Input() vuelos !: VueloCompleto[];

  existe: boolean = false
  vuelosBuscados$ !: Observable<VueloCompleto[]>;

  constructor( private buscServ: BuscadorService ){}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    

    if( this.vuelos !== undefined ){
      this.existe = true
    }

    this.vuelosBuscados$ = this.buscServ.getVuelosBuscados$()
    this.vuelosBuscados$.subscribe( vuelos =>{
      this.vuelos = vuelos

      if( vuelos.length > 0 ){

        this.existe = true
      }
      else{
        this.existe = false
      }
    })
  }
}
