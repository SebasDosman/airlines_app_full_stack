package co.edu.usbcali.airlinesapp.repository;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;
import co.edu.usbcali.airlinesapp.domain.Trayecto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrayectoRepository extends JpaRepository<Trayecto, Integer> {
    List<Trayecto> findAllByEstado(String estado);

//    @Query("SELECT t.vuelo.aeropuertoOrigen FROM Trayecto t WHERE t.idTrayecto = :idTrayecto")
//    Aeropuerto findAeropuertoByIdTrayecto(@Param("idTrayecto") Integer idTrayecto);
}
