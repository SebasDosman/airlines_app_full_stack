package co.edu.usbcali.airlinesapp.repository;

import co.edu.usbcali.airlinesapp.domain.TipoAsiento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoAsientoRepository extends JpaRepository<TipoAsiento, Integer> {
    List<TipoAsiento> findAllByEstado(String estado);
}
