package co.edu.usbcali.airlinesapp.repository;

import co.edu.usbcali.airlinesapp.domain.Factura;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    List<Factura> findAllByEstado(String estado);
    List<Factura> findAllByReserva_IdReserva(Integer idReserva);
}
