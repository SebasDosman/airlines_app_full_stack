
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Avion, Aeropuerto } from '../../interfaces/Aeropuerto';
import { AeropuertosService } from '../../services/aeropuertos.service';
import { Vuelo, Trayecto } from '../../interfaces/Vuelo';
import { VuelosService } from '../../services/vuelos.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-crear-vuelo',
  templateUrl: './crear-vuelo.component.html',
  styleUrls: ['./crear-vuelo.component.css']
})
export class CrearVueloComponent {



  // Mensajes de error
  errorAsientos: boolean    = false
  errorPrecioTotal: boolean = false
  errorEscalas: boolean     = false
  errorAsiento: string      = ""
  errorAeropuertos: boolean = false

  mostrarEscalas: boolean[] = [ false, false, false, false, false ]

  // Datos desde db
  aviones     !: Avion[]      ;
  aeropuertos !: Aeropuerto[] ;

  fechas = {

    fechaPrimerEscala   : "",
    fechaPrimerEscala2  : "",

    fechaSegundaEscala  : "",
    fechaSegundaEscala2 : "",

    fechaTercerEscala   : "",
    fechaTercerEscala2  : "",

    fechaCuartaEscala   : "",
    fechaCuartaEscala2  : "",

    fechaQuintaEscala   : "",
    fechaQuintaEscala2  : ""
  }



  constructor(  private fb: FormBuilder,
                private aeroServ: AeropuertosService,
                private VueloServ: VuelosService,
                private toastr  : ToastrService ){}


  crearForm: FormGroup = this.fb.group({
    n_escalas                 : [ '1', [ Validators.required ]],
    precioBase                : [, [ Validators.required ]],
    precio_asiento_economica  : [],
    precio_asiento_normal     : [],
    precio_asiento_ejecutivo  : [],

    aeropuerto_s_1: [],
    aeropuerto_s_2: [],
    aeropuerto_s_3: [],
    aeropuerto_s_4: [],
    aeropuerto_s_5: [],

    aeropuerto_f_1: [],
    aeropuerto_f_2: [],
    aeropuerto_f_3: [],
    aeropuerto_f_4: [],
    aeropuerto_f_5: [],


    fecha_s_1     : [],
    fecha_s_2     : [],
    fecha_s_3     : [],
    fecha_s_4     : [],
    fecha_s_5     : [],

    fecha_f_1     : [],
    fecha_f_2     : [],
    fecha_f_3     : [],
    fecha_f_4     : [],
    fecha_f_5     : [],


    id_avion_1: [],
    id_avion_2: [],
    id_avion_3: [],
    id_avion_4: [],
    id_avion_5: []
  })




  validarFechasEscalas ( ){
    const form = this.crearForm.controls

    this.fechas.fechaPrimerEscala2 = form["fecha_s_1"].value

    this.fechas.fechaSegundaEscala = form["fecha_f_1"].value
    this.fechas.fechaSegundaEscala2= form["fecha_s_2"].value

    this.fechas.fechaTercerEscala  = form["fecha_f_2"].value
    this.fechas.fechaTercerEscala2 = form["fecha_s_3"].value

    this.fechas.fechaCuartaEscala  = form["fecha_f_3"].value
    this.fechas.fechaCuartaEscala2 = form["fecha_s_4"].value

    this.fechas.fechaQuintaEscala  = form["fecha_f_4"].value
    this.fechas.fechaQuintaEscala2 = form["fecha_s_5"].value

    // Desactivar formularios que aún no se pueden tocar    

    if( form['fecha_s_1'].value == null  ) this.crearForm.get("fecha_f_1")?.disable()
    else this.crearForm.get("fecha_f_1")?.enable()


    // Desactivar fecha segunda escala
    if( form['fecha_f_1'].value == null  ) this.crearForm.get("fecha_s_2")?.disable()
    else this.crearForm.get("fecha_s_2")?.enable()
    

    if( form['fecha_s_2'].value == null ) this.crearForm.get("fecha_f_2")?.disable()
    else this.crearForm.get("fecha_f_2")?.enable()
  

    // Desactivar fecha tercer escala
    if( form['fecha_f_2'].value == null  ) this.crearForm.get("fecha_s_3")?.disable()
    else this.crearForm.get("fecha_s_3")?.enable()
    

    if( form['fecha_s_3'].value == null  ) this.crearForm.get("fecha_f_3")?.disable()
    else this.crearForm.get("fecha_f_3")?.enable()
    

    // Desactivar fecha cuarta escala
    if( form['fecha_f_3'].value == null ) this.crearForm.get("fecha_s_4")?.disable()
    else this.crearForm.get("fecha_s_4")?.enable()

    if( form['fecha_s_4'].value == null) this.crearForm.get("fecha_f_4")?.disable()
    else this.crearForm.get("fecha_f_4")?.enable()


    // Desactivar fecha quinta escala
    if( form['fecha_f_4'].value == null ) this.crearForm.get("fecha_s_5")?.disable()
    else this.crearForm.get("fecha_s_5")?.enable()

    if( form['fecha_s_5'].value == null ) this.crearForm.get("fecha_f_5")?.disable()
    else this.crearForm.get("fecha_f_5")?.enable()
  }


  validarHorasEscalas(): boolean {

    const form = this.crearForm.controls

    const fechaSalida1  = form['fecha_s_1'].value
    const fechaLlegada1 = form['fecha_f_1'].value

    const fechaSalida2  = form['fecha_s_2'].value
    const fechaLlegada2 = form['fecha_f_2'].value

    const fechaSalida3  = form['fecha_s_3'].value
    const fechaLlegada3 = form['fecha_f_3'].value

    const fechaSalida4  = form['fecha_s_4'].value
    const fechaLlegada4 = form['fecha_f_4'].value

    const fechaSalida5  = form['fecha_s_5'].value
    const fechaLlegada5 = form['fecha_f_5'].value


    // Fecha de salida y llegada 1 no pueden ser iguales o menor la llegada
    if( Date.parse( fechaSalida1) == Date.parse( fechaLlegada1 ) || Date.parse( fechaSalida1 ) > Date.parse( fechaLlegada1 )){
      this.errorAsiento = "Las fechas de la escala 1 no son correctas"
      return true
    }

    //La fecha de salida 2 no puede ser menor o igual a la llegada1
    if( Date.parse( fechaLlegada1 ) == Date.parse( fechaSalida2 ) || Date.parse( fechaLlegada1 ) > Date.parse( fechaSalida2 )){
      this.errorAsiento = "Las fechas de llegada de la escala 1 y salida de escala 2 son invalidas"
      return true
    }

    //Solo validacion en la 2
    if( Date.parse( fechaSalida2 ) == Date.parse( fechaLlegada2 ) || Date.parse( fechaSalida2 ) > Date.parse( fechaLlegada2 )){
      this.errorAsiento = "Las fechas de la escala 2 no son correctas"
      return true
    }

    //Validacin entre 2 y 3 
    if( Date.parse( fechaLlegada2 ) == Date.parse( fechaSalida3 ) || Date.parse( fechaLlegada2 ) > Date.parse( fechaSalida3 )){
      this.errorAsiento = "Las fechas de llegada de la escala 2 y salida de escala 3 son invalidas"
      return true
    }


    //Solo validacion en la 3
    if( Date.parse( fechaSalida3 ) == Date.parse( fechaLlegada3 ) || Date.parse( fechaSalida3 ) > Date.parse( fechaLlegada3 )){
      this.errorAsiento = "Las fechas de la escala 3 no son correctas"
      return true
    }

    //Validacion entre 3 y 4
    if( Date.parse( fechaLlegada3 ) == Date.parse( fechaSalida4 ) || Date.parse( fechaLlegada3 ) > Date.parse( fechaSalida4 )){
      this.errorAsiento = "Las fechas de llegada de la escala 3 y salida de escala 4 son invalidas"
      return true
    }

    //Solo validacion en la 4
     if( Date.parse( fechaSalida4 ) == Date.parse( fechaLlegada4 ) || Date.parse( fechaSalida4 ) > Date.parse( fechaLlegada4 )){
      this.errorAsiento = "Las fechas de la escala 4 no son correctas"
      return true
    }


    //Validacion entre 4 y 5
    if( Date.parse( fechaLlegada4 ) == Date.parse( fechaSalida5 ) || Date.parse( fechaLlegada4 ) > Date.parse( fechaSalida5 )){
      this.errorAsiento = "Las fechas de llegada de la escala 4 y salida de escala 5 son invalidas"
      return true
    }


    //Solo validacion en la 5
     if( Date.parse( fechaSalida5 ) == Date.parse( fechaLlegada5 ) || Date.parse( fechaSalida5 ) > Date.parse( fechaLlegada5 )){
      this.errorAsiento = "Las fechas de la escala 5 no son correctas"
      return true
    }

    this.errorAsiento = ""
    return false
  }

  ngOnInit(): void {

    this.fechas.fechaPrimerEscala = new Date().toISOString().slice(0, 16)
    
    this.cambioNEscalas()
    this.obtenerAeripuertos()
    this.obtenerAviones()
    this.validarFechasEscalas()

    this.crearForm.get('aeropuerto_s_2')?.disable();
    this.crearForm.get('aeropuerto_s_3')?.disable();
    this.crearForm.get('aeropuerto_s_4')?.disable();
    this.crearForm.get('aeropuerto_s_5')?.disable();
  }

  obtenerAeripuertos(){
    this.aeroServ.getAeropuertos().subscribe( aeropuertos =>{
      this.aeropuertos = aeropuertos
    })
  }

  obtenerAviones(){
    this.aeroServ.getAviones().subscribe( avion =>{
      this.aviones = avion
    })
  }


  validarAeropuertosEscalas(): boolean{

    const form = this.crearForm.controls

    let arregloIdSalidas = []
    let arregloIdLlegadas= []


    for( let i = 1; i < 6; i++ ){

      if( form[`aeropuerto_s_${i}`].value  !== null  && form[`aeropuerto_f_${i}`].value !== null ){
        
        arregloIdSalidas.push( form[`aeropuerto_s_${i}`].value )
        arregloIdLlegadas.push( form[`aeropuerto_f_${i}`].value )
      }
    }

    if( this.validarIdSeRepite( arregloIdSalidas ) ){

      this.errorAeropuertos = true
      return true
    } else{

      //Validar con los aeropuertos de llegada
      if( this.validarIdSeRepite( arregloIdLlegadas ) ){
        this.errorAeropuertos = true
        return true
      }
      else{
        this.errorAeropuertos = false
        return false
      }
    }
  }


  validarIdSeRepite( array: string[] ): boolean {

    
    let valorRetorno: boolean= false
    let repetidos: { [x: string]: any; } = {};

    array.forEach( (number: string) =>{

      repetidos[ number ] = (repetidos[number] || 0) + 1;

      if( repetidos[number ] > 1 ){
                
        valorRetorno = true    
      }
    });

    return valorRetorno
  }



  nombreAeropuerto( numero: number ): string{

    if( numero == 2 && this.crearForm.controls['aeropuerto_f_1'].value !== null ){
      const aero = this.aeropuertos.filter( aero => aero.idAeropuerto == this.crearForm.controls['aeropuerto_f_1'].value )[0]
      this.crearForm.get('aeropuerto_s_2')?.setValue(`${ aero.idAeropuerto }`)
      return aero.nombre
    }

    if( numero == 3 && this.crearForm.controls['aeropuerto_f_2'].value !== null ){
      const aero = this.aeropuertos.filter( aero => aero.idAeropuerto == this.crearForm.controls['aeropuerto_f_2'].value )[0]  
      this.crearForm.get('aeropuerto_s_3')?.setValue(`${ aero.idAeropuerto }`)
      return aero.nombre
    }

    if( numero == 4 && this.crearForm.controls['aeropuerto_f_3'].value !== null ){
      const aero = this.aeropuertos.filter( aero => aero.idAeropuerto == this.crearForm.controls['aeropuerto_f_3'].value )[0]  
      this.crearForm.get('aeropuerto_s_4')?.setValue(`${ aero.idAeropuerto }`)
      return aero.nombre
    }

    if( numero == 5 && this.crearForm.controls['aeropuerto_f_4'].value !== null ){
      const aero = this.aeropuertos.filter( aero => aero.idAeropuerto == this.crearForm.controls['aeropuerto_f_4'].value )[0]  
      this.crearForm.get('aeropuerto_s_5')?.setValue(`${ aero.idAeropuerto }`)
      return aero.nombre
    }

    return ""
  }



  enviarInformacion(){

    if( this.hayDatosErroneos() ) return

    const informacion = this.crearForm

    const vueloEnviar: Vuelo = {
      idAeropuertoOrigen    : parseInt( informacion.controls['aeropuerto_s_1'].value )          ,
      idAeropuertoDestino   : parseInt( this.obtenerDestinoYHoraLLegada()[0]  )                 ,
      precio                : parseInt( informacion.controls['precioBase'].value )              ,
      horaLlegada           : this.obtenerDestinoYHoraLLegada()[1]                  ,
      horaSalida            : informacion.controls['fecha_s_1'].value               ,
      precioAsientoVip      : parseInt( informacion.controls['precio_asiento_ejecutivo'].value ),
      precioAsientoNormal   : parseInt( informacion.controls['precio_asiento_normal']   .value ),
      precioAsientoBasico   : parseInt( informacion.controls['precio_asiento_economica'].value ),
      estado                : 'A'
    }



    this.VueloServ.postVuelo( vueloEnviar )?.subscribe(
      vuelo =>{
        if( vuelo == null ){

          this.toastr.error("No se pudo crear el vuelo", "Error")
          return;
        } if( vuelo.idVuelo == undefined ){

          console.log("El idVuelo es undefined")
          return
        } else{

          this.enviarTrayectos( vuelo.idVuelo )
          console.log( vuelo )
          this.toastr.success("Vuelo creado con éxito", "Éxito")
          this.crearForm.reset()
        }
      },
      err => {
        let errorMessage = "Error al crear el vuelo";

        if (err.error && err.error.mensaje) {
          errorMessage = err.error.mensaje;
        }
        
        this.toastr.error(errorMessage, "Error");
      })
  }

  enviarTrayectos( idVuelo: number  ){
    const numeroEscalas = parseInt( this.crearForm.value['n_escalas'] )
    for( let i = 1; i < ( numeroEscalas + 1 ); i ++ ){

      this.enviarTrayecto( idVuelo, i )
    }
  }


  enviarTrayecto( idVuelo: number , iterator: number ): boolean {


    const info = this.crearForm.controls
    const trayectoInfo: Trayecto = {
      idVuelo             : idVuelo                                   ,
      idAvion             : parseInt( info[`id_avion_${iterator }`].value   ),
      idAeropuertoOrigen  : parseInt( info[`aeropuerto_s_${iterator}`].value),
      idAeropuertoDestino : parseInt( info[`aeropuerto_f_${iterator}`].value),
      horaSalida          : info[`fecha_s_${iterator }`].value     ,
      horaLlegada         : info[`fecha_f_${iterator }`].value     ,
      estado              : 'A'                                       ,
    }


    this.VueloServ.postTrayecto( trayectoInfo )?.subscribe( trayecto =>{
      if( trayecto  == null ){
        this.toastr.error("No se pudo crear el trayecto", "Error")
        return false
      }
      return true
    })
    
    return false
  }

  obtenerDestinoYHoraLLegada(){

    const informacion = this.crearForm
    const numeroEscalas = parseInt( this.crearForm.value['n_escalas'] )

    if( numeroEscalas === 1 ){
      return [ informacion.controls['aeropuerto_f_1'].value, informacion.controls['fecha_f_1'].value ]
    }
    else if( numeroEscalas === 2 ){
      return [ informacion.controls['aeropuerto_f_2'].value, informacion.controls['fecha_f_2'].value ]
    }
    else if( numeroEscalas === 3 ){
      return [ informacion.controls['aeropuerto_f_3'].value, informacion.controls['fecha_f_3'].value ]
    }
    else if( numeroEscalas === 4 ){
      return [ informacion.controls['aeropuerto_f_4'].value, informacion.controls['fecha_f_4'].value ]
    }
    else{
      return [ informacion.controls['aeropuerto_f_5'].value, informacion.controls['fecha_f_5'].value ]
    }
  }

  hayDatosErroneos(): boolean {

    const informacion = this.crearForm
    const precioAsientosError: boolean = informacion.controls['precio_asiento_economica'].value < 10000  || informacion.controls['precio_asiento_normal'].value < 10000 || informacion.controls['precio_asiento_ejecutivo'].value < 10000


    if( this.validarAeropuertosEscalas() ) return true
    if( this.validarHorasEscalas() ) return true
    

    if( !informacion.controls['precioBase'].valid  || informacion.controls['precioBase'].value < 50000 ){
      this.errorPrecioTotal = true
      this.errorAsientos  = false
      this.errorEscalas   = false
      return true
    }


    else if( !informacion.controls['precio_asiento_economica'].valid  || !informacion.controls['precio_asiento_normal'].valid || !informacion.controls['precio_asiento_ejecutivo'].valid || precioAsientosError){

      this.errorPrecioTotal  = false
      this.errorAsientos     = true
      this.errorEscalas      = false
      return true
    }

    else if( this.hayErrorEscalas( ) ){
      this.errorPrecioTotal  = false
      this.errorAsientos     = false
      this.errorEscalas      = true
      return true
    }

    this.errorPrecioTotal  = false
    this.errorAsientos     = false
    this.errorEscalas      = false
    return false
  }


  cambioNEscalas(){
    const numeroEscalas = parseInt( this.crearForm.value['n_escalas'] )

    this.mostrarEscalas = [ false, false, false, false, false ]

    for( let i = 0; i < numeroEscalas; i++ ){
      this.mostrarEscalas[i] = true
    }
  }


  hayErrorEscalas(): boolean {

    const numeroEscalas = parseInt( this.crearForm.value['n_escalas'])
    //Escala 1

    const condicion_e_1: boolean =  this.crearForm.controls['aeropuerto_s_1'].value == null || this.crearForm.controls['aeropuerto_f_1'].value == null || this.crearForm.controls['fecha_s_1'].value == null || this.crearForm.controls['id_avion_1'].value == null
    const condicion_e_2: boolean =  this.crearForm.controls['aeropuerto_s_2'].value == null || this.crearForm.controls['aeropuerto_f_2'].value == null || this.crearForm.controls['fecha_s_2'].value == null || this.crearForm.controls['id_avion_2'].value == null
    const condicion_e_3: boolean =  this.crearForm.controls['aeropuerto_s_3'].value == null || this.crearForm.controls['aeropuerto_f_3'].value == null || this.crearForm.controls['fecha_s_3'].value == null || this.crearForm.controls['id_avion_3'].value == null
    const condicion_e_4: boolean =  this.crearForm.controls['aeropuerto_s_4'].value == null || this.crearForm.controls['aeropuerto_f_4'].value == null || this.crearForm.controls['fecha_s_4'].value == null || this.crearForm.controls['id_avion_4'].value == null
    const condicion_e_5: boolean =  this.crearForm.controls['aeropuerto_s_5'].value == null || this.crearForm.controls['aeropuerto_f_5'].value == null || this.crearForm.controls['fecha_s_5'].value == null || this.crearForm.controls['id_avion_5'].value == null


    //En la escala 2 no nos importan los valores de la escala 3, 4 y 5, pero en las escalas superiores si nos importa la 2, por ejemplo en la escala 5 validamos todas las anteriores, pero en la 1, solo esa
    const validarEscalas1: boolean = numeroEscalas == 1 || numeroEscalas == 2 || numeroEscalas == 3 || numeroEscalas == 4 || numeroEscalas == 5
    const validarEscalas2: boolean = numeroEscalas == 2 || numeroEscalas == 3 || numeroEscalas == 4 || numeroEscalas == 5
    const validarEscalas3: boolean = numeroEscalas == 3 || numeroEscalas == 4 || numeroEscalas == 5
    const validarEscalas4: boolean = numeroEscalas == 4 || numeroEscalas == 5

    if(  validarEscalas1 && condicion_e_1 ){

      return true
    }
    //Escala 2
    if( validarEscalas2 && condicion_e_2 ){

      return true
    }
    //Escala 3
    if( validarEscalas3 && condicion_e_3 && condicion_e_2 && condicion_e_1 ){
      return true
    }
    //Escala 4
    if( validarEscalas4 && condicion_e_4 && condicion_e_3 && condicion_e_2 && condicion_e_1 ){
      return true
    }
    //Escala 5
    if( numeroEscalas == 5 && condicion_e_5 && condicion_e_4 && condicion_e_3 && condicion_e_2 && condicion_e_1 ){
      return true
    }

    return false
  }


}
