import { Asiento, Trayecto } from "./Vuelo";
import { Vuelo } from "./Vuelo";
import { Avion, Aeropuerto } from './Aeropuerto';


export interface Reserva{
    idReserva   ?: number;
    idVuelo      : number | undefined;
    idAsiento    : number | undefined;
    idUsuario    : number | undefined;
    precioTotal  : number;
    estadoPago   : string;
    fecha        : string;
    estado       : string;
}

export interface Factura{

    idFactura ?: number;
    idReserva  : number | undefined;
    fecha      : string | undefined;
    estado     : string;
}

export interface ReservaCompleta{
    reservaEnv     : Reserva       ;
    vueloEnv       : Vuelo         ;
    escalasEnv     : Trayecto[]    ;
    avionesEnv     : Avion[]       ;
    aeropuertosEnv : Aeropuerto[]  ;
    asientoEnv     : Asiento;
}