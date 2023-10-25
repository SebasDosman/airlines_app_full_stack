package co.edu.usbcali.airlinesapp.repository;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Integer> {
    List<Aeropuerto> findAllByEstado(String estado);
    boolean existsByIata(String iata);
}
