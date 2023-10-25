package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.VueloDTO;

import java.util.List;

public interface VueloService {
    VueloDTO guardarVuelo(VueloDTO vueloDTO) throws Exception; //Tested
    List<VueloDTO> obtenerVuelos(); //Tested
    List<VueloDTO> obtenerVuelosActivos(); //Tested
    VueloDTO obtenerVueloPorId(Integer id) throws Exception; //Tested
    VueloDTO actualizarVuelo(VueloDTO vueloDTO) throws Exception; //Tested
    VueloDTO eliminarVuelo(Integer id) throws Exception; //Not tested
}
