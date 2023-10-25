package co.edu.usbcali.airlinesapp.mappers;

import co.edu.usbcali.airlinesapp.domain.TipoAsiento;
import co.edu.usbcali.airlinesapp.dtos.TipoAsientoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TipoAsientoMapper {
    public static TipoAsientoDTO domainToDTO(TipoAsiento tipoAsiento) {
        return TipoAsientoDTO.builder()
                .idTipoAsiento(tipoAsiento.getIdTipoAsiento())
                .descripcion(tipoAsiento.getDescripcion())
                .estado(tipoAsiento.getEstado())
                .build();
    }

    public static List<TipoAsientoDTO> domainToDTOList(List<TipoAsiento> tipoAsientos) {
        return tipoAsientos.stream().map(TipoAsientoMapper::domainToDTO).collect(Collectors.toList());
    }

    public static TipoAsiento dtoToDomain(TipoAsientoDTO tipoAsientoDTO) {
        return TipoAsiento.builder()
                .idTipoAsiento(tipoAsientoDTO.getIdTipoAsiento())
                .descripcion(tipoAsientoDTO.getDescripcion())
                .estado(tipoAsientoDTO.getEstado())
                .build();
    }

    public static List<TipoAsiento> dtoToDomainList(List<TipoAsientoDTO> tipoAsientosDTO) {
        return tipoAsientosDTO.stream().map(TipoAsientoMapper::dtoToDomain).collect(Collectors.toList());
    }
}
