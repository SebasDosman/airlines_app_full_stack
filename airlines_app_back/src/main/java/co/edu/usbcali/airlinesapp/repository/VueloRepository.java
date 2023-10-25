package co.edu.usbcali.airlinesapp.repository;

import co.edu.usbcali.airlinesapp.domain.Vuelo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
    List<Vuelo> findAllByEstado(String estado);
}
