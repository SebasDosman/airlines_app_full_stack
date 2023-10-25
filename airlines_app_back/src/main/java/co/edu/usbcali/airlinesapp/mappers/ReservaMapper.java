package co.edu.usbcali.airlinesapp.mappers;

import co.edu.usbcali.airlinesapp.domain.Reserva;
import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReservaMapper {
    public static ReservaDTO domainToDTO(Reserva reserva) {
        return ReservaDTO.builder()
                .idReserva(reserva.getIdReserva())
                .idVuelo(reserva.getVuelo() != null ? reserva.getVuelo().getIdVuelo() : null)
                .idAsiento(reserva.getAsiento() != null ? reserva.getAsiento().getIdAsiento() : null)
                .idUsuario(reserva.getUsuario() != null ? reserva.getUsuario().getIdUsuario() : null)
                .precioTotal(reserva.getPrecioTotal())
                .estadoPago(reserva.getEstadoPago())
                .fecha(reserva.getFecha())
                .estado(reserva.getEstado())
                .build();
    }

    public static List<ReservaDTO> domainToDTOList(List<Reserva> reservas) {
        return reservas.stream().map(ReservaMapper::domainToDTO).collect(Collectors.toList());
    }

    public static Reserva dtoToDomain(ReservaDTO reservaDTO) {
        return Reserva.builder()
                .idReserva(reservaDTO.getIdReserva())
                .precioTotal(reservaDTO.getPrecioTotal())
                .estadoPago(reservaDTO.getEstadoPago())
                .fecha(reservaDTO.getFecha())
                .estado(reservaDTO.getEstado())
                .build();
    }

    public static List<Reserva> dtoToDomainList(List<ReservaDTO> reservasDTO) {
        return reservasDTO.stream().map(ReservaMapper::dtoToDomain).collect(Collectors.toList());
    }
}
