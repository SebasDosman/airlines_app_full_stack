package co.edu.usbcali.airlinesapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class TipoAsientoDTO {
        private Integer idTipoAsiento;
        private String descripcion;
        private String estado;
}
