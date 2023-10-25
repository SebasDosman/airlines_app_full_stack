package co.edu.usbcali.airlinesapp.repository;

import co.edu.usbcali.airlinesapp.domain.RolUsuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Integer> {
    List<RolUsuario> findAllByEstado(String estado);
}
