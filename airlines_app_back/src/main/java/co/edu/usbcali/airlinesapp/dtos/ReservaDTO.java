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
public class ReservaDTO {
        private Integer idReserva;
        private Integer idVuelo;
        private Integer idAsiento;
        private Integer idUsuario;
        private long precioTotal;
        private String estadoPago;
        private Date fecha;
        private String estado;
}
