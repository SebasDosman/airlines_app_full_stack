package co.edu.usbcali.airlinesapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@ToString
public class TrayectoDTO {
        private Integer idTrayecto;
        private Integer idAvion;
        private Integer idAeropuertoOrigen;
        private Integer idAeropuertoDestino;
        private Date horaSalida;
        private Date horaLlegada;
        private Integer idVuelo;
        private String estado;
}
