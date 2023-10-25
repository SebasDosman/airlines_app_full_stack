package co.edu.usbcali.airlinesapp.mappers;

import co.edu.usbcali.airlinesapp.domain.Trayecto;
import co.edu.usbcali.airlinesapp.dtos.TrayectoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TrayectoMapper {
    public static TrayectoDTO domainToDTO(Trayecto trayecto) {
        return TrayectoDTO.builder()
                .idTrayecto(trayecto.getIdTrayecto())
                .idAvion(trayecto.getAvion() != null ? trayecto.getAvion().getIdAvion() : null)
                .idAeropuertoOrigen(trayecto.getAeropuertoOrigen() != null ? trayecto.getAeropuertoOrigen().getIdAeropuerto() : null)
                .idAeropuertoDestino(trayecto.getAeropuertoDestino() != null ? trayecto.getAeropuertoDestino().getIdAeropuerto() : null)
                .horaSalida(trayecto.getHoraSalida())
                .horaLlegada(trayecto.getHoraLlegada())
                .idVuelo(trayecto.getVuelo() != null ? trayecto.getVuelo().getIdVuelo() : null)
                .estado(trayecto.getEstado())
                .idVuelo( trayecto.getVuelo().getIdVuelo() )
                .build();
    }

    public static List<TrayectoDTO> domainToDTOList(List<Trayecto> trayectos) {
        return trayectos.stream().map(TrayectoMapper::domainToDTO).collect(Collectors.toList());
    }

    public static Trayecto dtoToDomain(TrayectoDTO trayectoDTO) {
        return Trayecto.builder()
                .idTrayecto(trayectoDTO.getIdTrayecto())
                .horaSalida(trayectoDTO.getHoraSalida())
                .horaLlegada(trayectoDTO.getHoraLlegada())
                .estado(trayectoDTO.getEstado())
                .build();
    }

    public static List<Trayecto> dtoToDomainList(List<TrayectoDTO> trayectosDTO) {
        return trayectosDTO.stream().map(TrayectoMapper::dtoToDomain).collect(Collectors.toList());
    }
}
