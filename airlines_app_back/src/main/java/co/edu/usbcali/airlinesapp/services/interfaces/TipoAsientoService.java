package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.TipoAsientoDTO;

import java.util.List;

public interface TipoAsientoService {
    TipoAsientoDTO guardarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception; //Tested
    List<TipoAsientoDTO> obtenerTipoAsientos(); //Tested
    List<TipoAsientoDTO> obtenerTipoAsientosActivos(); //Tested
    TipoAsientoDTO obtenerTipoAsientoPorId(Integer id) throws Exception; //Tested
    TipoAsientoDTO actualizarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception; //Tested
    TipoAsientoDTO eliminarTipoAsiento(Integer id) throws Exception; //Not tested
}
