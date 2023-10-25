import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Reserva } from 'src/app/interfaces/Reserva';
import { Usuario } from 'src/app/interfaces/Usuarios';
import { ReservaService } from 'src/app/services/reserva.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-usuarios-activos',
  templateUrl: './usuarios-activos.component.html',
  styleUrls: ['./usuarios-activos.component.css']
})
export class UsuariosActivosComponent {
  errorCedula   : boolean = false
  datosLista    : Usuario[] = []
  errorUsuario  : boolean = false
  usuario      !: Usuario

  

  constructor(  private fb          : FormBuilder   ,
                private userServ    : UsuarioService ){}

  cedulaForm: FormGroup = this.fb.group({
    cedula: [, [ Validators.required, Validators.pattern(/^[1-9]\d{5,11}$/) ]]
  })


  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.userServ.obtenerUsuarios().subscribe( res =>{
      this.datosLista = res
    })
  }

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
        this.datosLista = []
        this.datosLista.push( this.usuario )
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
