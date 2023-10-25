import { Component } from '@angular/core';

import { Vuelo } from 'src/app/interfaces/Vuelo';
import { VuelosService } from '../../services/vuelos.service';
import { VueloCompleto } from '../../interfaces/Vuelo';


@Component({
  selector: 'app-seleccionar-vuelo',
  templateUrl: './seleccionar-vuelo.component.html',
  styleUrls: ['./seleccionar-vuelo.component.css']
})

export class SeleccionarVueloComponent {
  vuelos: VueloCompleto[] = []
  
  constructor( private vueloServ: VuelosService ){}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    
    this.obtenerVuelos().then( res => {
      this.vuelos = res 
    }) 
  }


  obtenerVuelos(): Promise<any[]>{
    return this.vueloServ.obtenerVueloConTodaLaData()
  }
}
