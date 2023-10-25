import { Component } from '@angular/core';

import { Observable } from 'rxjs';
import { VueloCompleto } from 'src/app/interfaces/Vuelo';
import { BuscadorService } from '../../services/buscador.service';
import { VuelosService } from '../../services/vuelos.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})

export class MainPageComponent {
  vuelosBuscador  !: VueloCompleto[]
  vuelos          !: VueloCompleto[]
  vuelosBuscados$ !: Observable<VueloCompleto[]>
  existe           : boolean = false
  yaCargado        : boolean = false

  constructor(  private buscServ  : BuscadorService,
                private vueloServ : VuelosService ){}

    ngOnInit(): void {
      //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
      //Add 'implements OnInit' to the class.
      
    this.obtenerVuelos().then( res => {
      this.vuelosBuscador = res 
    }) 



    this.vuelosBuscados$ = this.buscServ.getVuelosBuscados$()
    this.vuelosBuscados$.subscribe( vuelos =>{
      this.vuelos = vuelos
      this.yaCargado = true
      if( vuelos.length > 0 ){

        this.existe = true
      }
      else{
        this.existe = false
      }
    })
    }
 

  obtenerVuelos(): Promise<any[]>{
    return this.vueloServ.obtenerVueloConTodaLaData()
  }
}
