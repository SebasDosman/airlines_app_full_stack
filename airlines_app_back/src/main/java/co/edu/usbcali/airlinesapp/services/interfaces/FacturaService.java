package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.FacturaDTO;

import java.util.List;

public interface FacturaService {
    FacturaDTO guardarFactura(FacturaDTO facturaDTO) throws Exception; //Tested (Non functional)
    List<FacturaDTO> obtenerFacturas(); //Tested
    List<FacturaDTO> obtenerFacturasActivas(); //Tested
    List<FacturaDTO> obtenerFacturasPorIdReserva(Integer idReserva) throws Exception; //Not tested
    FacturaDTO obtenerFacturaPorId(Integer id) throws Exception; //Tested
    FacturaDTO actualizarFactura(FacturaDTO facturaDTO) throws Exception; //Tested (Non functional)
    FacturaDTO eliminarFactura(Integer id) throws Exception; //Not tested
}
