package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws Exception; //Tested
    List<UsuarioDTO> obtenerUsuarios(); //Tested
    List<UsuarioDTO> obtenerUsuariosActivos(); //Tested
    UsuarioDTO obtenerUsuarioPorId(Integer id) throws Exception; //Tested
    UsuarioDTO obtenerUsuarioPorCedula(String cedula) throws Exception; //Tested
    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) throws Exception; //Tested
    UsuarioDTO eliminarUsuario(Integer id) throws Exception; //Not tested
}
