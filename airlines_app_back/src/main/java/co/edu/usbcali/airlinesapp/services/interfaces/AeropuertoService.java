package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;

import java.util.List;

public interface AeropuertoService {
    AeropuertoDTO guardarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception; //Tested
    List<AeropuertoDTO> obtenerAeropuertos(); //Tested
    List<AeropuertoDTO> obtenerAeropuertosActivos(); //Tested
    AeropuertoDTO obtenerAeropuertoPorId(Integer id) throws Exception; //Tested
    AeropuertoDTO actualizarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception; //Tested
    AeropuertoDTO eliminarAeropuerto(Integer id) throws Exception; //Not tested
}
