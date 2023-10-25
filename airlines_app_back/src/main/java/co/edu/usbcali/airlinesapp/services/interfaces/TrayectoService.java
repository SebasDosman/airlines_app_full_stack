package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;
import co.edu.usbcali.airlinesapp.dtos.TrayectoDTO;

import java.util.List;

public interface TrayectoService {
    TrayectoDTO guardarTrayecto(TrayectoDTO trayectoDTO) throws Exception; //Tested (Non functional)
    List<TrayectoDTO> obtenerTrayectos(); //Tested
    List<TrayectoDTO> obtenerTrayectosActivos(); //Tested
    TrayectoDTO obtenerTrayectoPorId(Integer id) throws Exception; //Tested
    TrayectoDTO actualizarTrayecto(TrayectoDTO trayectoDTO) throws Exception; //Tested (Non functional)
    TrayectoDTO eliminarTrayecto(Integer id) throws Exception; //Not tested

//    AeropuertoDTO obtenerAeropuertoOrigenPorIdTrayecto(Integer idTrayecto) throws Exception;
}
