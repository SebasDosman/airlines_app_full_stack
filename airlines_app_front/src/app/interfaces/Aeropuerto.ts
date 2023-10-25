export interface Aeropuerto{
    idAeropuerto?: any;
    nombre      : string;
    ubicacion   : string;
    iata        : string;
    estado      : string;
}

export interface Avion{
    idAvion    ?: string;
    modelo      : string;
    aerolinea   : string;
    estado      : string;
}