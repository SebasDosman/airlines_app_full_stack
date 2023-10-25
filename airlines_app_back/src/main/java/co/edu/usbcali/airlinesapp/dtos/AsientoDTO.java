package co.edu.usbcali.airlinesapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class AsientoDTO {
        private Integer idAsiento;
        private Integer idTipoAsiento;
        private Integer idAvion;
        private String ubicacion;
        private String estado;
}
