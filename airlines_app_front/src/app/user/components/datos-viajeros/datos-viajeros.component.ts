import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Usuario } from 'src/app/interfaces/Usuarios';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-datos-viajeros',
  templateUrl: './datos-viajeros.component.html',
  styleUrls: ['./datos-viajeros.component.css']
})
export class DatosViajerosComponent {

  errorCedula   : boolean = false
  existeUsuario : boolean = false
  activarForm   : boolean = false

  errorDatosUsuario : boolean = false
  errorCorreoUsuario: boolean = false


  usuario!: Usuario | undefined;

  constructor(  private fb      : FormBuilder,
                private userServ: UsuarioService,
                private toastr  : ToastrService ){}


  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.

  }





  cedulaForm: FormGroup = this.fb.group({
    cedula: [, [ Validators.required, Validators.pattern(/^[1-9]\d{5,11}$/) ]]
  })

  usuarioForm: FormGroup = this.fb.group({
    nombre  : [, [ Validators.required, Validators.maxLength( 25 )]],
    apellido: [, [ Validators.required, Validators.maxLength( 25 )]],
    correo  : [, [ Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]]
  })

  enviarUsuario(){

    this.usuario = undefined
    if( this.hayErrorFormularioUsuario( ) ) return

    const informacion: Usuario = {
      idRolUsuario: '1'                               ,
      cedula      : this.cedulaForm.value['cedula']   ,
      nombre      : this.usuarioForm.value['nombre']  ,
      apellido    : this.usuarioForm.value['apellido'],
      correo      : this.usuarioForm.value['correo']  ,
      estado      : 'A'
    }

    this.userServ.crearUsuario( informacion )
    .subscribe(
      res =>{
        this.usuario = res
        this.activarForm = false

        this.userServ.cambiarUsuarioReserva( this.usuario )
      },
      err => {
        let errorMessage = "Error al crear el aeropuerto";

        if (err.error && err.error.mensaje) {
          errorMessage = err.error.mensaje;
        }

        this.toastr.error(errorMessage, "Error");
      })
  }


  enviarCedula(){

    this.usuario = undefined

    if( this.hayErrorCedula() ) return

    this.userServ.obtenerUsuarioByCedula( this.cedulaForm.value['cedula'] )
    .subscribe(
      res => {

      if( res == null ){
        this.activarForm    = true
      }
      else{

        this.activarForm    = false
        this.usuario = res
        this.userServ.cambiarUsuarioReserva( this.usuario )
      }
      })
  }

  hayErrorFormularioUsuario(): boolean {

    const informacion = this.usuarioForm
    if( informacion.controls['nombre'].invalid || informacion.controls['apellido'].invalid ){
      this.errorDatosUsuario  = true
      this.errorCorreoUsuario = false
      return true
    }

    else if( informacion.controls['correo'].invalid ){
      this.errorCorreoUsuario = true
      this.errorDatosUsuario  = false
      return true
    }

    this.errorCorreoUsuario = false
    this.errorDatosUsuario  = false
    return false
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
