package co.edu.usbcali.airlinesapp.repository;

import co.edu.usbcali.airlinesapp.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findAllByEstado(String estado);
    Usuario getReferenceByCedula(String cedula);
    boolean existsByCedula(String cedula);
    boolean existsByCorreo(String email);
}
