package co.edu.usbcali.airlinesapp.repository;

import co.edu.usbcali.airlinesapp.domain.Asiento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsientoRepository extends JpaRepository<Asiento, Integer> {
    List<Asiento> findAllByEstado(String estado);
}
