package co.edu.usbcali.airlinesapp.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class VueloDTO {
    private Integer idVuelo;
    private Integer idAeropuertoOrigen;
    private Integer idAeropuertoDestino;
    private long precio;
    private Date horaSalida;
    private Date horaLlegada;
    private long precioAsientoVip;
    private long precioAsientoNormal;
    private long precioAsientoBasico;
    private String estado;
}
