package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.RolUsuarioDTO;

import java.util.List;

public interface RolUsuarioService {
    RolUsuarioDTO guardarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception; //Tested
    List<RolUsuarioDTO> obtenerRolUsuarios(); //Tested
    List<RolUsuarioDTO> obtenerRolUsuariosActivos(); //Tested
    RolUsuarioDTO obtenerRolUsuarioPorId(Integer id) throws Exception; //Tested
    RolUsuarioDTO actualizarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception; //Tested
    RolUsuarioDTO eliminarRolUsuario(Integer id) throws Exception; //Not tested
}
