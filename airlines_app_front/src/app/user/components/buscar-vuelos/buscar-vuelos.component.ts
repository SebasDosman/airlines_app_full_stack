import { Component, Input } from '@angular/core';
import { VueloCompleto } from '../../../interfaces/Vuelo';
import { Aeropuerto } from '../../../interfaces/Aeropuerto';
import { AeropuertosService } from '../../../services/aeropuertos.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BuscadorService } from '../../../services/buscador.service';

@Component({
  selector: 'app-buscar-vuelos',
  templateUrl: './buscar-vuelos.component.html',
  styleUrls: ['./buscar-vuelos.component.css']
})
export class BuscarVuelosComponent {

  @Input()vuelos !: VueloCompleto[];
  aeropuertos    !: Aeropuerto[];
  errorFormulario : boolean = false
  vuelosBuscados !: VueloCompleto[];

  constructor(  private aeroServ: AeropuertosService,
                private fb      : FormBuilder,
                private buscServ: BuscadorService ){}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.aeroServ.getAeropuertos().subscribe( res => {
      this.aeropuertos = res

      this.aeropuertos = this.aeropuertos.filter( aero => aero.estado === "A")  
    } )
  }



  ubiForm: FormGroup = this.fb.group({
    origen  : [, [ Validators.required ]],
    destino : [, [ Validators.required ]]
  })



  enviarInformacion(){

    if( this.validarFormulario( ) ) return
    console.log( this.ubiForm.value )

    this.filtroVuelosComunes( this.ubiForm.value['origen'], this.ubiForm.value['destino'] )
  }

  filtroVuelosComunes( origen: string, destino: string){

      this.vuelosBuscados = this.vuelos.filter        ( vuelo => vuelo.aeropuertoOrigen.idAeropuerto === parseInt( origen ) )
      this.vuelosBuscados = this.vuelosBuscados.filter( vuelo => vuelo.aeropuertoDestino.idAeropuerto === parseInt( destino ))
      this.buscServ.setVuelosBuscados( this.vuelosBuscados )
      this.ubiForm.reset()
  }


  validarFormulario(): boolean {
    if( this.ubiForm.invalid ){

      this.errorFormulario = true
      return true
    }
    this.errorFormulario = false
    return false
  }
}
