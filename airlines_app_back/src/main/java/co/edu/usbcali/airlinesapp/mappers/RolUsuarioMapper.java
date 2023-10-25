package co.edu.usbcali.airlinesapp.mappers;

import co.edu.usbcali.airlinesapp.domain.Avion;
import co.edu.usbcali.airlinesapp.domain.RolUsuario;
import co.edu.usbcali.airlinesapp.dtos.AvionDTO;
import co.edu.usbcali.airlinesapp.dtos.RolUsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RolUsuarioMapper {
    public static RolUsuarioDTO domainToDTO(RolUsuario rolUsuario) {
        return RolUsuarioDTO.builder()
                .idRolUsuario(rolUsuario.getIdRolUsuario())
                .descripcion(rolUsuario.getDescripcion())
                .estado(rolUsuario.getEstado())
                .build();
    }

    public static List<RolUsuarioDTO> domainToDTOList(List<RolUsuario> rolUsuarios) {
        return rolUsuarios.stream().map(RolUsuarioMapper::domainToDTO).collect(Collectors.toList());
    }

    public static RolUsuario dtoToDomain(RolUsuarioDTO rolUsuarioDTO) {
        return RolUsuario.builder()
                .idRolUsuario(rolUsuarioDTO.getIdRolUsuario())
                .descripcion(rolUsuarioDTO.getDescripcion())
                .estado(rolUsuarioDTO.getEstado())
                .build();
    }

    public static List<RolUsuario> dtoToDomainList(List<RolUsuarioDTO> rolUsuariosDTO) {
        return rolUsuariosDTO.stream().map(RolUsuarioMapper::dtoToDomain).collect(Collectors.toList());
    }
}
