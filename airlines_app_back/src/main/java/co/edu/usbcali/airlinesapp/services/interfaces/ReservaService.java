package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;

import java.util.List;

public interface ReservaService {
    ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception; //Tested (Non functional)
    List<ReservaDTO> obtenerReservas(); //Tested
    List<ReservaDTO> obtenerReservasActivas(); //Tested
    List<ReservaDTO> obtenerReservasPorIdVuelo(Integer idVuelo) throws Exception; //Not tested
    List<ReservaDTO> obtenerReservasPorCedula(String cedula) throws Exception; //Not tested
    ReservaDTO obtenerReservaPorId(Integer id) throws Exception; //Tested
    ReservaDTO actualizarReserva(ReservaDTO reservaDTO) throws Exception; //Tested (Non functional)
    ReservaDTO eliminarReserva(Integer id) throws Exception; //Not tested
}
