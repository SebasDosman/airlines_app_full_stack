import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AeropuertosService } from '../../services/aeropuertos.service';
import { Avion } from '../../interfaces/Aeropuerto';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-anadir-avion',
  templateUrl: './anadir-avion.component.html',
  styleUrls: ['./anadir-avion.component.css']
})
export class AnadirAvionComponent {

  constructor(  private fb        : FormBuilder,
                private avionServ : AeropuertosService,
                private toastr  : ToastrService ){}

  errorModelo   : boolean = false
  errorAerolinea: boolean = false

  avionForm: FormGroup = this.fb.group({
    modelo: [, [Validators.required, Validators.maxLength( 25 ) ]],
    aerolinea: [,[ Validators.required, Validators.maxLength(25) ] ]
  })



  guardarInformacion(){

    if( this.validarHayError() ) return


    const avionInformacion: Avion = {
      modelo : this.avionForm.value['modelo'],
      aerolinea: this.avionForm.value['aerolinea'],
      estado: "1"
    }

    this.avionServ.postAvion( avionInformacion )?.subscribe(
      data =>{
        if( data == null ){
          this.toastr.error("No se pudo crear el avión", "Error")
          return;
        } else {
          this.toastr.success("Avión creado con éxito", "Éxito")
          this.avionForm.reset()
        }
      },
      err => {
        let errorMessage = "Error al crear el avión";

        if (err.error && err.error.mensaje) {
          errorMessage = err.error.mensaje;
        }

        this.toastr.error(errorMessage, "Error");
      })
  }



  validarHayError(): boolean{

    const informacion = this.avionForm
    if( informacion.controls['modelo'].status == "INVALID"){
      this.errorModelo = true
      this.errorAerolinea = false
      return true
    }
    else if( informacion.controls['aerolinea'].status =="INVALID"){
      this.errorAerolinea = true
      this.errorModelo = false
      return true
    }

    this.errorModelo    = false
    this.errorAerolinea = false
    return false
  }
}
