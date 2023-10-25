package co.edu.usbcali.airlinesapp.mappers;

import co.edu.usbcali.airlinesapp.domain.Usuario;
import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {
    public static UsuarioDTO domainToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .idRolUsuario(usuario.getRolUsuario() != null ? usuario.getRolUsuario().getIdRolUsuario() : null)
                .cedula(usuario.getCedula())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .estado(usuario.getEstado())
                .build();
    }

    public static List<UsuarioDTO> domainToDTOList(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::domainToDTO).collect(Collectors.toList());
    }

    public static Usuario dtoToDomain(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .idUsuario(usuarioDTO.getIdUsuario())
                .cedula(usuarioDTO.getCedula())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .correo(usuarioDTO.getCorreo())
                .estado(usuarioDTO.getEstado())
                .build();
    }

    public static List<Usuario> dtoToDomainList(List<UsuarioDTO> usuariosDTO) {
        return usuariosDTO.stream().map(UsuarioMapper::dtoToDomain).collect(Collectors.toList());
    }
}
