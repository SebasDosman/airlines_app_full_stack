package co.edu.usbcali.airlinesapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class UsuarioDTO {
        private Integer idUsuario;

        private Integer idRolUsuario;
        private String cedula;
        private String nombre;
        private String apellido;
        private String correo;
        private String estado;
}
