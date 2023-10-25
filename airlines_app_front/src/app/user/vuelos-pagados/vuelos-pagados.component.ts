import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Reserva } from 'src/app/interfaces/Reserva';
import { Usuario } from 'src/app/interfaces/Usuarios';
import { ReservaService } from 'src/app/services/reserva.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-vuelos-pagados',
  templateUrl: './vuelos-pagados.component.html',
  styleUrls: ['./vuelos-pagados.component.css']
})
export class VuelosPagadosComponent {
  errorCedula   : boolean = false
  datosLista    : Reserva[] = []
  errorUsuario  : boolean = false
  usuario      !: Usuario

  reservasMostrar : Reserva[] = []

  constructor(  private fb          : FormBuilder   ,
                private userServ    : UsuarioService, 
                private reservaServ : ReservaService){}

  cedulaForm: FormGroup = this.fb.group({
    cedula: [, [ Validators.required, Validators.pattern(/^[1-9]\d{5,11}$/) ]]
  })


  enviarCedula(){
 
    if( this.hayErrorCedula() ) return

    this.errorUsuario = false
    this.userServ.obtenerUsuarioByCedula( this.cedulaForm.value['cedula'] )
    .subscribe( res => {

      if( res == null ){
        this.errorUsuario = true
      }
      else{

        this.errorUsuario = false
        this.usuario      = res
        this.obtenerReservasUsuario()
      }
    })
  }

  //TODO obtener las reservas por el vuelo y mostrarlas
  obtenerReservasUsuario(){
    
    this.reservaServ.obtenerReservasPorUsuario( this.usuario.cedula ).subscribe( reservas =>{
       this.reservasMostrar = reservas.filter( reserva => reserva.estadoPago == "A" && reserva.estado == "A")
  
        if( this.reservasMostrar.length == 0 ){
          this.errorUsuario = true
        }
        else {
          this.errorUsuario = false
        }
    })
  }


  hayErrorCedula(): boolean {

    if( this.cedulaForm.invalid ) {

      this.errorCedula  = true
      return true
    }

    this.errorCedula    = false
    return false
  }
}
