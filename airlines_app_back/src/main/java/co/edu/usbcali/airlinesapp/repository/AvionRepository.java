package co.edu.usbcali.airlinesapp.repository;

import co.edu.usbcali.airlinesapp.domain.Avion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvionRepository extends JpaRepository<Avion, Integer> {
    List<Avion> findAllByEstado(String estado);
}
