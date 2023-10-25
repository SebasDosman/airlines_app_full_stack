package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;
import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;
import co.edu.usbcali.airlinesapp.mappers.AeropuertoMapper;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.AeropuertoService;

import co.edu.usbcali.airlinesapp.utility.ConstantesUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class AeropuertoServiceImpl implements AeropuertoService {
    private final AeropuertoRepository aeropuertoRepository;

    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    private AeropuertoDTO guardarOActualizarAeropuerto(AeropuertoDTO aeropuertoDTO) {
        Aeropuerto aeropuerto = AeropuertoMapper.dtoToDomain(aeropuertoDTO);

        return AeropuertoMapper.domainToDTO(aeropuertoRepository.save(aeropuerto));
    }

    private void validarAeropuertoDTO(AeropuertoDTO aeropuertoDTO, boolean esGuardar) throws Exception {
        if (aeropuertoDTO == null) {
            throw new Exception("El aeropuerto no puede ser nulo");
        } if (aeropuertoDTO.getNombre() == null || aeropuertoDTO.getNombre().isBlank() || aeropuertoDTO.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del aeropuerto no puede ser nulo  o vacío");
        } if (!Pattern.matches(ConstantesUtility.PATTERN_NAME_REGEX, aeropuertoDTO.getNombre())) {
            throw new Exception("El nombre del aeropuerto solo puede contener letras y espacios");
        } if (aeropuertoDTO.getIata() == null || aeropuertoDTO.getIata().isBlank() || aeropuertoDTO.getIata().trim().isEmpty()) {
            throw new Exception("El IATA del aeropuerto no puede ser nulo o vacío");
        } if (!Pattern.matches(ConstantesUtility.PATTERN_IATA_REGEX, aeropuertoDTO.getIata())) {
            throw new Exception("El IATA del aeropuerto debe tener 3 caracteres y solo letras mayúsculas");
        } if (aeropuertoDTO.getUbicacion() == null || aeropuertoDTO.getUbicacion().isBlank() || aeropuertoDTO.getUbicacion().trim().isEmpty()) {
            throw new Exception("La ubicación del aeropuerto no puede ser nula o vacía");
        } if (aeropuertoDTO.getEstado() == null || aeropuertoDTO.getEstado().isBlank() || aeropuertoDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del aeropuerto no puede ser nulo o vacío");
        }

        if (esGuardar) {
            if (aeropuertoDTO.getIdAeropuerto() != null) {
                throw new Exception("El id del aeropuerto no debe existir");
            } if (aeropuertoRepository.existsByIata(aeropuertoDTO.getIata())) {
                throw new Exception("El aeropuerto con IATA " + aeropuertoDTO.getIata() + " ya existe");
            }
        }

        if (!esGuardar) {
            if (!aeropuertoRepository.existsById(aeropuertoDTO.getIdAeropuerto())) {
                throw new Exception("El aeropuerto con id " + aeropuertoDTO.getIdAeropuerto() + " no existe");
            }
        }
    }

    @Override
    public AeropuertoDTO guardarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception {
        validarAeropuertoDTO(aeropuertoDTO, true);

        return guardarOActualizarAeropuerto(aeropuertoDTO);
    }

    @Override
    public List<AeropuertoDTO> obtenerAeropuertos() {
        return AeropuertoMapper.domainToDTOList(aeropuertoRepository.findAll());
    }

    @Override
    public List<AeropuertoDTO> obtenerAeropuertosActivos() {
        return AeropuertoMapper.domainToDTOList(aeropuertoRepository.findAllByEstado("A"));
    }

    @Override
    public AeropuertoDTO obtenerAeropuertoPorId(Integer id) throws Exception {
        if (!aeropuertoRepository.existsById(id)) {
            throw new Exception("El aeropuerto con id " + id + " no existe");
        }

        return AeropuertoMapper.domainToDTO(aeropuertoRepository.getReferenceById(id));
    }

    @Override
    public AeropuertoDTO actualizarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception {
        validarAeropuertoDTO(aeropuertoDTO, false);

        return guardarOActualizarAeropuerto(aeropuertoDTO);
    }

    @Override
    public AeropuertoDTO eliminarAeropuerto(Integer id) throws Exception {
        AeropuertoDTO aeropuertoSavedDTO = obtenerAeropuertoPorId(id);

        aeropuertoSavedDTO.setEstado("I");

        return guardarOActualizarAeropuerto(aeropuertoSavedDTO);
    }
}
