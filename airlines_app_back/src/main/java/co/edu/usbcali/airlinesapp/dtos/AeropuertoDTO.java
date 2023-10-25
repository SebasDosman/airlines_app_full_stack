package co.edu.usbcali.airlinesapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class AeropuertoDTO {
        private Integer idAeropuerto;
        private String nombre;
        private String iata;
        private String ubicacion;
        private String estado;
}
