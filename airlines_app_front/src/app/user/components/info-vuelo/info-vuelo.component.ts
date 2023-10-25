import { Component, Input } from '@angular/core';
import { Vuelo, Trayecto, VueloCompleto, TrayectoCompleto } from '../../../interfaces/Vuelo';
import { Aeropuerto, Avion } from '../../../interfaces/Aeropuerto';
import { AeropuertosService } from '../../../services/aeropuertos.service';
import { LoginComponent } from '../../../auth/login/login.component';

@Component({
  selector: 'app-info-vuelo',
  templateUrl: './info-vuelo.component.html',
  styleUrls: ['./info-vuelo.component.css']
})
export class InfoVueloComponent {

  @Input() vuelo     !: Vuelo         
  @Input() trayectos !: Trayecto[]    

  trayectosCompletos !: TrayectoCompleto[]
  aeropuertos        !: Aeropuerto  

  aeropuertosPrincipales : Aeropuerto[] = []
  

  constructor( private aeroServ: AeropuertosService ){
    
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.


    this.obtenerAeropuertosPrincipales()
  }

  

  obtenerTrayectosCompletos(){
    
    this.trayectosCompletos = []
    for( let i = 0; i < this.trayectos.length; i ++ ){

      const { idTrayecto, horaSalida, horaLlegada, idVuelo, estado } = this.trayectos[i]

      let avion: Avion
      let aeropuertoOrigen: Aeropuerto
      let aeropuertoDestino: Aeropuerto

      this.aeroServ.getAvionById( this.trayectos[i].idAvion + "")
      .subscribe( res =>{
        
        avion = res
        
        
        this.aeroServ.getAeropuertoById( this.trayectos[i].idAeropuertoOrigen + "")
        .subscribe( res => {

          aeropuertoOrigen = res
          
          this.aeroServ.getAeropuertoById( this.trayectos[i].idAeropuertoDestino + "")
          .subscribe( res =>{

            aeropuertoDestino = res


            const trayectoAnadir = { idTrayecto, avion, aeropuertoOrigen, aeropuertoDestino, horaSalida, horaLlegada, idVuelo, estado }
            this.trayectosCompletos.push( trayectoAnadir )

            
            this.trayectosCompletos.sort( (a, b) => new Date( a.horaSalida ).getTime() - new Date( b.horaSalida ).getTime() )
            
          })
        })
      })
    }
  }

  obtenerAeropuertosPrincipales(){
    const numeroTrayectos = this.trayectos.length
    

    this.aeroServ.getAeropuertoById( this.trayectos[0].idAeropuertoOrigen + "" ).subscribe( res =>{
      this.aeropuertosPrincipales.push( res )

      this.aeroServ.getAeropuertoById( this.trayectos[ this.trayectos.length - 1 ].idAeropuertoDestino + "" ).subscribe( res =>{
        this.aeropuertosPrincipales.push( res )
        

          console.log( this.aeropuertosPrincipales )
        //Se ejecuta aqui la operacion porque en este punto los trayectos ya no son undefined
        this.obtenerTrayectosCompletos()
      })
    })
  }
}
