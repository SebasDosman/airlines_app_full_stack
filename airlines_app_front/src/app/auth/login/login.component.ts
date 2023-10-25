import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Usuario } from 'src/app/interfaces/Usuarios';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  isNotValid: boolean = false

  usuario = {
    correo    : "",
    cedula: ""
  }

  constructor(  private fb      : FormBuilder,
                private router  : Router     ,
                private userServ: UsuarioService,
                private toastr : ToastrService ){}

  sesionForm: FormGroup = this.fb.group({
    correo    : [, [ Validators.required ]],
    cedula: [, [ Validators.required ]]
  })

  iniciarSesion(){
    this.usuario.correo = this.sesionForm.value["correo"]
    this.usuario.cedula = this.sesionForm.value["cedula"]

    this.userServ.obtenerUsuarioByCedula( this.usuario.cedula ).subscribe( res =>{

      if( res !== null ){
        this.existeUsuario( res )
      }
      else{
        this.isNotValid = true
      }
    })
  }


  existeUsuario( res: Usuario ){
    if( res.idRolUsuario == "2"){
      if( res.correo == this.usuario.correo ){

        localStorage.setItem( "permiso", JSON.stringify( true )! )
        this.router.navigate( ["administrador"] )
        this.isNotValid = false
      }
      this.isNotValid = true
    }
  }
}
