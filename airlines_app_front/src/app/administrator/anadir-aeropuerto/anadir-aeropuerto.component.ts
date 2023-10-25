import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UbicacionesService } from '../../services/ubicaciones.service';
import { AeropuertosService } from '../../services/aeropuertos.service';
import { Pais } from '../../interfaces/Ubicaciones';
import { Aeropuerto } from '../../interfaces/Aeropuerto';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-anadir-aeropuerto',
  templateUrl: './anadir-aeropuerto.component.html',
  styleUrls: ['./anadir-aeropuerto.component.css']
})
export class AnadirAeropuertoComponent {

  paises  : Pais[]    = []
  ciudades: string[] = []
  loading : boolean       = true

  errorNombre: boolean = false
  errorCampos: boolean = false
  errorIata  : boolean = false
  existeIata : boolean = false

  constructor(  private fb      : FormBuilder,
                private ubiServ : UbicacionesService,
                private aeroServ: AeropuertosService,
                private toastr  : ToastrService ){}


  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.ubiServ.getPaises().subscribe( data =>{
      this.paises = data.data
      this.loading = false
    })
  }

  aeropuertoForm: FormGroup = this.fb.group({
    nombre: [, [ Validators.required, Validators.maxLength( 25 ), Validators.minLength(5) ]],
    pais  : [, [ Validators.required] ],
    ciudad: [, [ Validators.required] ],
    iata  : [, [Validators.required, Validators.maxLength( 3 ), Validators.minLength(3) ] ]
  })


  hayErrorCamposFormulario(): boolean {

    const informacion = this.aeropuertoForm
    
    if( informacion.controls['nombre'].status == "INVALID"){

      this.errorNombre  = true
      this.errorCampos  = false
      this.errorIata    = false

      return true
    }
    else if( informacion.controls['iata'].status == "INVALID" ){
      this.errorNombre  = false
      this.errorCampos  = false
      this.errorIata    = true

      return true
    }
    else if( informacion.controls['pais'].status == "INVALID" || informacion.controls['ciudad'].status == "INVALID"){

      this.errorIata    = false
      this.errorNombre  = false
      this.errorCampos  = true
      return true
    }

    this.errorCampos  = false
    this.errorIata    = false
    this.errorNombre  = false
    return false
  }



  buscarCiudades(){
    this.ciudades = []
    const pais = this.paises.filter( pais => pais.country == this.aeropuertoForm.value['pais'] )
    this.ciudades = pais[0].cities
  }

  guardarAeropuerto(){
    if( this.hayErrorCamposFormulario() ) return

    const informacion = this.aeropuertoForm.value

    const aeropuertoInformacion : Aeropuerto = {
      nombre    : informacion.nombre,
      ubicacion : `${informacion.pais} - ${informacion.ciudad}`,
      iata      : informacion.iata,
      estado    : 'A'
    }

    let aeropuertosAntiguos: Aeropuerto[] = []

    this.aeroServ.getAeropuertos().subscribe( res => {
      aeropuertosAntiguos = res
      if( this.validarIataExistente( aeropuertosAntiguos, aeropuertoInformacion.iata ) ) return


      this.enviarInformacion( aeropuertoInformacion )
      this.aeropuertoForm.reset()
    })



  }


  enviarInformacion( aeropuertoInformacion: Aeropuerto ){
    this.aeroServ.postAeropuertos( aeropuertoInformacion )?.subscribe(
      res => {
        if( res == null ){
          this.toastr.error("No se pudo crear el aeropuerto", "Error")
          return
        }

        this.toastr.success("Aeropuerto creado con éxito", "Éxito")
      },
      err => {
        let errorMessage = "Error al crear el aeropuerto";

        if (err.error && err.error.mensaje) {
          errorMessage = err.error.mensaje;
        }

        this.toastr.error(errorMessage, "Error");
      })
  }


  validarIataExistente( aeropuertos: Aeropuerto[], iata: string ): boolean{
    const respuesta = aeropuertos.filter( res => res.iata == iata )

    if( respuesta.length == 0 ) {
      
      this.existeIata = false
      return false
    }
    this.existeIata = true
    return true
  }

}
