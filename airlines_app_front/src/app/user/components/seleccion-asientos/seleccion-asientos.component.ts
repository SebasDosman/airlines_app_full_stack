import { Component, Input } from '@angular/core';
import { UsuarioService } from 'src/app/services/usuario.service';
import { VuelosService } from 'src/app/services/vuelos.service';
import { Usuario } from '../../../interfaces/Usuarios';
import { ListaAsientos, Vuelo } from 'src/app/interfaces/Vuelo';
import { Asiento } from '../../../interfaces/Vuelo';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReservaService } from 'src/app/services/reserva.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-seleccion-asientos',
  templateUrl: './seleccion-asientos.component.html',
  styleUrls: ['./seleccion-asientos.component.css']
})
export class SeleccionAsientosComponent {

  @Input() vuelo !: Vuelo;
  usuario              !: Usuario | undefined;
  errorAsiento          : boolean = false
  idAsientoSeleccionado : string = ""
  asientosOcupados      : Asiento[] = []


  asientos: ListaAsientos = {
    asientos_vip        : [
      { ubi: "v0", libre: true }  ,  { ubi: "v1"  , libre: true },  { ubi: "v2", libre: true },  { ubi: "v3", libre: true },  { ubi: "v4", libre: true },  { ubi: "v5", libre: true },  { ubi: "v6", libre: true },  { ubi: "v7", libre: true },  { ubi: "v8", libre: true },  { ubi: "v9", libre: true },
      { ubi: "v10", libre: true } ,  { ubi: "v11", libre: true },  { ubi: "v12", libre: true },  { ubi: "v13", libre: true },  { ubi: "v14", libre: true },  { ubi: "v15", libre: true },  { ubi: "v16", libre: true },  { ubi: "v17", libre: true },  { ubi: "v18", libre: true },  { ubi: "v19", libre: true },
      { ubi: "v20", libre: true } ,  { ubi: "v21", libre: true },  { ubi: "v22", libre: true },  { ubi: "v23", libre: true },  { ubi: "v24", libre: true },  { ubi: "v25", libre: true },  { ubi: "v26", libre: true },  { ubi: "v27", libre: true },  { ubi: "v28", libre: true },  { ubi: "v29", libre: true }
    ],
    asientos_normal     : [
      { ubi: "n0", libre: true }  ,  { ubi: "n1"  , libre: true },  { ubi: "n2", libre: true },  { ubi: "n3", libre: true },  { ubi: "n4", libre: true },  { ubi: "n5", libre: true },  { ubi: "n6", libre: true },  { ubi: "n7", libre: true },  { ubi: "n8", libre: true },  { ubi: "n9", libre: true },
      { ubi: "n10", libre: true } ,  { ubi: "n11", libre: true },  { ubi: "n12", libre: true },  { ubi: "n13", libre: true },  { ubi: "n14", libre: true },  { ubi: "n15", libre: true },  { ubi: "n16", libre: true },  { ubi: "n17", libre: true },  { ubi: "n18", libre: true },  { ubi: "n19", libre: true },
      { ubi: "n20", libre: true } ,  { ubi: "n21", libre: true },  { ubi: "n22", libre: true },  { ubi: "n23", libre: true },  { ubi: "n24", libre: true },  { ubi: "n25", libre: true },  { ubi: "n26", libre: true },  { ubi: "n27", libre: true },  { ubi: "n28", libre: true },  { ubi: "n29", libre: true },
      { ubi: "n30", libre: true } ,  { ubi: "n31", libre: true },  { ubi: "n32", libre: true },  { ubi: "n33", libre: true },  { ubi: "n34", libre: true },  { ubi: "n35", libre: true },  { ubi: "n36", libre: true },  { ubi: "n37", libre: true },  { ubi: "n38", libre: true },  { ubi: "n39", libre: true },
      { ubi: "n40", libre: true } ,  { ubi: "n41", libre: true },  { ubi: "n42", libre: true },  { ubi: "n43", libre: true },  { ubi: "n44", libre: true },  { ubi: "n45", libre: true },  { ubi: "n46", libre: true },  { ubi: "n47", libre: true },  { ubi: "n48", libre: true },  { ubi: "n49", libre: true },
      { ubi: "n50", libre: true } ,  { ubi: "n51", libre: true },  { ubi: "n52", libre: true },  { ubi: "n53", libre: true },  { ubi: "n54", libre: true },  { ubi: "n55", libre: true },  { ubi: "n56", libre: true },  { ubi: "n57", libre: true },  { ubi: "n58", libre: true },  { ubi: "n59", libre: true },
      { ubi: "n60", libre: true } ,  { ubi: "n61", libre: true },  { ubi: "n62", libre: true },  { ubi: "n63", libre: true },  { ubi: "n64", libre: true },  { ubi: "n65", libre: true },  { ubi: "n66", libre: true },  { ubi: "n67", libre: true },  { ubi: "n68", libre: true },  { ubi: "n69", libre: true }

    ],
    asientos_economicos : [
      { ubi: "e0" , libre: true }  ,  { ubi: "e1"  , libre: true },  { ubi: "e2", libre: true },  { ubi: "e3", libre: true },  { ubi: "e4", libre: true },  { ubi: "e5", libre: true },  { ubi: "e6", libre: true },  { ubi: "e7", libre: true },  { ubi: "e8", libre: true },  { ubi: "e9", libre: true },
      { ubi: "e10", libre: true } ,  { ubi: "e11", libre: true },  { ubi: "e12", libre: true },  { ubi: "e13", libre: true },  { ubi: "e14", libre: true },  { ubi: "e15", libre: true },  { ubi: "e16", libre: true },  { ubi: "e17", libre: true },  { ubi: "e18", libre: true },  { ubi: "e19", libre: true },
      { ubi: "e20", libre: true } ,  { ubi: "e21", libre: true },  { ubi: "e22", libre: true },  { ubi: "e23", libre: true },  { ubi: "e24", libre: true },  { ubi: "e25", libre: true },  { ubi: "e26", libre: true },  { ubi: "e27", libre: true },  { ubi: "e28", libre: true },  { ubi: "e29", libre: true },
      { ubi: "e30", libre: true } ,  { ubi: "e31", libre: true },  { ubi: "e32", libre: true },  { ubi: "e33", libre: true },  { ubi: "e34", libre: true },  { ubi: "e35", libre: true },  { ubi: "e36", libre: true },  { ubi: "e37", libre: true },  { ubi: "e38", libre: true },  { ubi: "e39", libre: true },
      { ubi: "e40", libre: true } ,  { ubi: "e41", libre: true },  { ubi: "e42", libre: true },  { ubi: "e43", libre: true },  { ubi: "e44", libre: true },  { ubi: "e45", libre: true },  { ubi: "e46", libre: true },  { ubi: "e47", libre: true },  { ubi: "e48", libre: true },  { ubi: "e49", libre: true },
      { ubi: "e50", libre: true } ,  { ubi: "e51", libre: true },  { ubi: "e52", libre: true },  { ubi: "e53", libre: true },  { ubi: "e54", libre: true },  { ubi: "e55", libre: true },  { ubi: "e56", libre: true },  { ubi: "e57", libre: true },  { ubi: "e58", libre: true },  { ubi: "e59", libre: true },
      { ubi: "e60", libre: true } ,  { ubi: "e61", libre: true },  { ubi: "e62", libre: true },  { ubi: "e63", libre: true },  { ubi: "e64", libre: true },  { ubi: "e65", libre: true },  { ubi: "e66", libre: true },  { ubi: "e67", libre: true },  { ubi: "e68", libre: true },  { ubi: "e69", libre: true },
      { ubi: "e70", libre: true } ,  { ubi: "e71", libre: true },  { ubi: "e72", libre: true },  { ubi: "e73", libre: true },  { ubi: "e74", libre: true },  { ubi: "e75", libre: true },  { ubi: "e76", libre: true },  { ubi: "e77", libre: true },  { ubi: "e78", libre: true },  { ubi: "e79", libre: true },
      { ubi: "e80", libre: true } ,  { ubi: "e81", libre: true },  { ubi: "e82", libre: true },  { ubi: "e83", libre: true },  { ubi: "e84", libre: true },  { ubi: "e85", libre: true },  { ubi: "e86", libre: true },  { ubi: "e87", libre: true },  { ubi: "e88", libre: true },  { ubi: "e89", libre: true },
      { ubi: "e90", libre: true } ,  { ubi: "e91", libre: true },  { ubi: "e92", libre: true },  { ubi: "e93", libre: true },  { ubi: "e94", libre: true },  { ubi: "e95", libre: true },  { ubi: "e96", libre: true },  { ubi: "e97", libre: true },  { ubi: "e98", libre: true },  { ubi: "e99", libre: true }
    ]
  }


  constructor(  private userServ  : UsuarioService,
                private vueloServ : VuelosService,
                private reservaSer: ReservaService,
                private toastr    : ToastrService  ){}



  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.

    this.userServ.getUsuarioReserva$().subscribe( res => {

      this.usuario = res
      this.bloquearAsientosOcupados()
    })
  }

  bloquearAsientosOcupados(){

    this.reservaSer.obtenerReservasVuelo( this.vuelo.idVuelo )
    .subscribe( (res) => {

      for( let i = 0; i < res.length; i ++ ){

        this.reservaSer.getAsiento( res[i].idAsiento ).subscribe( asiento=>{

          this.asientosOcupados.push( asiento )
          this.bloquearAsientosEnLista( asiento )
        })
      }
    })
  }



  bloquearAsientosEnLista( asientoOcupado: Asiento ){

    let index = asientoOcupado.ubicacion.slice(1)
    if( asientoOcupado.idTipoAsiento == 1 ){

      this.asientos.asientos_vip[ parseInt( index ) ].libre = false
    }
    else if( asientoOcupado.idTipoAsiento == 2 ){

      this.asientos.asientos_normal[ parseInt( index ) ].libre = false
    }

    else if( asientoOcupado.idTipoAsiento == 3 ){

      this.asientos.asientos_economicos[ parseInt( index ) ].libre = false
    }

  }




  obtenerAsiento( idAsiento: string ){
    this.idAsientoSeleccionado = idAsiento

    
    

    if( idAsiento.slice(0,1) == "v" && this.asientos.asientos_vip[ parseInt( idAsiento.slice( 1 ) ) ].libre ){
      
      this.toastr.success(`Seleccionado asiento con Id ${ idAsiento }`, "Éxito")
    }

    else if( idAsiento.slice(0,1) == "e" && this.asientos.asientos_economicos[ parseInt( idAsiento.slice(1) )  ].libre ){
      this.toastr.success(`Seleccionado asiento con Id ${ idAsiento }`, "Éxito")
    }

    else if( idAsiento.slice(0,1) == "n" && this.asientos.asientos_normal[ parseInt( idAsiento.slice(1) )  ].libre ){
      this.toastr.success(`Seleccionado asiento con Id ${ idAsiento }`, "Éxito")
    }

    else{
      this.toastr.error(`Seleccionaste un asiento invalido`, "Error")
    }
  }



  crearReserva( ){
    let index: number = parseInt( this.idAsientoSeleccionado.slice(1) )


    //Validar que se escogio un asiento
    if( this.idAsientoSeleccionado == "" ){

      this.errorAsiento = true
      return
    }

    //Validar asiento tomado previamente
    if( this.idAsientoSeleccionado[0] == "v" && !this.asientos.asientos_vip[ index ].libre ){
      
      this.errorAsiento = true
      return
    }

    if( this.idAsientoSeleccionado[0] == "n" && !this.asientos.asientos_normal[ index ].libre ){
      
      this.errorAsiento = true
      return
    }

    if( this.idAsientoSeleccionado[0] == "e" && !this.asientos.asientos_economicos[ index ].libre ){
      
      this.errorAsiento = true
      return
    }

    this.errorAsiento = false
    this.reservaSer.cambiarIdAsiento( this.idAsientoSeleccionado )
  }

}
