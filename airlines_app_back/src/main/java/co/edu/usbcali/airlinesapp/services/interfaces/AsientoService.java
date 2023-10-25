package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.AsientoDTO;

import java.util.List;

public interface AsientoService {
    AsientoDTO guardarAsiento(AsientoDTO asientoDTO) throws Exception; //Tested
    List<AsientoDTO> obtenerAsientos(); //Tested
    List<AsientoDTO> obtenerAsientosActivos(); //Tested
    AsientoDTO obtenerAsientoPorId(Integer id) throws Exception; //Tested
    AsientoDTO actualizarAsiento(AsientoDTO asientoDTO) throws Exception; //Tested
    AsientoDTO eliminarAsiento(Integer id) throws Exception; //Not tested
}
