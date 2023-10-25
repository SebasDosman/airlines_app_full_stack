package co.edu.usbcali.airlinesapp.mappers;

import co.edu.usbcali.airlinesapp.domain.Asiento;
import co.edu.usbcali.airlinesapp.dtos.AsientoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AsientoMapper {
    public static AsientoDTO domainToDTO(Asiento asiento) {
        return AsientoDTO.builder()
                .idAsiento(asiento.getIdAsiento())
                .idTipoAsiento(asiento.getTipoAsiento() != null ? asiento.getTipoAsiento().getIdTipoAsiento() : null)
                .idAvion(asiento.getAvion() != null ? asiento.getAvion().getIdAvion() : null)
                .ubicacion(asiento.getUbicacion())
                .estado(asiento.getEstado())
                .build();
    }

    public static List<AsientoDTO> domainToDTOList(List<Asiento> asientos) {
        return asientos.stream().map(AsientoMapper::domainToDTO).collect(Collectors.toList());
    }

    public static Asiento dtoToDomain(AsientoDTO asientoDTO) {
        return Asiento.builder()
                .idAsiento(asientoDTO.getIdAsiento())
                .ubicacion(asientoDTO.getUbicacion())
                .estado(asientoDTO.getEstado())
                .build();
    }

    public static List<Asiento> dtoToDomainList(List<AsientoDTO> asientosDTO) {
        return asientosDTO.stream().map(AsientoMapper::dtoToDomain).collect(Collectors.toList());
    }
}
