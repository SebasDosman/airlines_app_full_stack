import { Aeropuerto, Avion } from './Aeropuerto';

export interface Vuelo{
    idVuelo              ?: number;
    idAeropuertoOrigen    : number;
    idAeropuertoDestino   : number;
    precio                : number;
    horaLlegada           : Date;
    horaSalida            : Date;
    precioAsientoBasico   : number;
    precioAsientoVip      : number;
    precioAsientoNormal   : number;
    estado                : string;

}


export interface VueloCompleto{
    idVuelo              ?: number;
    aeropuertoOrigen      : Aeropuerto;
    aeropuertoDestino     : Aeropuerto;
    precio                : number;
    horaLlegada           : Date;
    horaSalida            : Date;
    precioAsientoBasico   : number;
    precioAsientoVip      : number;
    precioAsientoNormal   : number;
    estado                : string;
}


export interface Trayecto{
    idTrayecto         ?: number;
    idAvion             : number;
    idAeropuertoOrigen  : number;
    idAeropuertoDestino : number;
    horaSalida          : Date  ;
    horaLlegada         : Date  ;
    idVuelo             : number;
    estado              : string;
}


export interface TrayectoCompleto{
    idTrayecto         ?: number;
    avion               : Avion;
    aeropuertoOrigen  : Aeropuerto;
    aeropuertoDestino : Aeropuerto;
    horaSalida          : Date  ;
    horaLlegada         : Date  ;
    idVuelo             : number;
    estado              : string;
}


export interface Asiento{
    idAsiento      ?: number;
    idTipoAsiento   : number;
    ubicacion       : string;
    estado          : string;
}

export interface AsientoLista{
    ubi     : string;
    libre   : boolean 
}

export interface ListaAsientos{
    asientos_vip        : AsientoLista[];
    asientos_normal     : AsientoLista[];
    asientos_economicos : AsientoLista[];
}