package co.edu.usbcali.airlinesapp.mappers;

import co.edu.usbcali.airlinesapp.domain.Avion;
import co.edu.usbcali.airlinesapp.dtos.AvionDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AvionMapper {
    public static AvionDTO domainToDTO(Avion avion) {
        return AvionDTO.builder()
                .idAvion(avion.getIdAvion())
                .modelo(avion.getModelo())
                .aerolinea(avion.getAerolinea())
                .estado(avion.getEstado())
                .build();
    }

    public static List<AvionDTO> domainToDTOList(List<Avion> aviones) {
        return aviones.stream().map(AvionMapper::domainToDTO).collect(Collectors.toList());
    }

    public static Avion dtoToDomain(AvionDTO avionDTO) {
        return Avion.builder()
                .idAvion(avionDTO.getIdAvion())
                .modelo(avionDTO.getModelo())
                .aerolinea(avionDTO.getAerolinea())
                .estado(avionDTO.getEstado())
                .build();
    }

    public static List<Avion> dtoToDomainList(List<AvionDTO> avionesDTO) {
        return avionesDTO.stream().map(AvionMapper::dtoToDomain).collect(Collectors.toList());
    }
}
