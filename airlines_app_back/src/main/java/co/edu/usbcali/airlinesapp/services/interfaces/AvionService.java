package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.AvionDTO;

import java.util.List;

public interface AvionService {
    AvionDTO guardarAvion(AvionDTO avionDTO) throws Exception; //Tested
    List<AvionDTO> obtenerAviones(); //Tested
    List<AvionDTO> obtenerAvionesActivos(); //Tested
    AvionDTO obtenerAvionPorId(Integer id) throws Exception; //Tested
    AvionDTO actualizarAvion(AvionDTO avionDTO) throws Exception; //Tested
    AvionDTO eliminarAvion(Integer id) throws Exception; //Not tested
}
