package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Asiento;
import co.edu.usbcali.airlinesapp.domain.Avion;
import co.edu.usbcali.airlinesapp.domain.TipoAsiento;
import co.edu.usbcali.airlinesapp.dtos.AsientoDTO;
import co.edu.usbcali.airlinesapp.mappers.AsientoMapper;
import co.edu.usbcali.airlinesapp.repository.AsientoRepository;
import co.edu.usbcali.airlinesapp.repository.AvionRepository;
import co.edu.usbcali.airlinesapp.repository.TipoAsientoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.AsientoService;

import co.edu.usbcali.airlinesapp.utility.ConstantesUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class AsientoServiceImpl implements AsientoService {
    private final AsientoRepository asientoRepository;
    private final TipoAsientoRepository tipoAsientoRepository;
    private final AvionRepository avionRepository;

    public AsientoServiceImpl(AsientoRepository asientoRepository, TipoAsientoRepository tipoAsientoRepository, AvionRepository avionRepository) {
        this.asientoRepository = asientoRepository;
        this.tipoAsientoRepository = tipoAsientoRepository;
        this.avionRepository = avionRepository;
    }

    private AsientoDTO guardarOAcutalizarAsiento(AsientoDTO asientoDTO) {
        Asiento asiento = AsientoMapper.dtoToDomain(asientoDTO);

        TipoAsiento tipoAsiento = tipoAsientoRepository.getReferenceById(asientoDTO.getIdTipoAsiento());
        Avion avion = avionRepository.getReferenceById(asientoDTO.getIdAvion());

        asiento.setTipoAsiento(tipoAsiento);
        asiento.setAvion(avion);

        return AsientoMapper.domainToDTO(asientoRepository.save(asiento));
    }

    private void validarAsientoDTO(AsientoDTO asientoDTO, boolean esGuardar) throws Exception {
        if (asientoDTO == null) {
            throw new Exception("El asiento no puede ser nulo");
        } if (asientoDTO.getIdTipoAsiento() == null || asientoDTO.getIdTipoAsiento() <= 0) {
            throw new Exception("El id del tipo de asiento no puede ser nulo o menor o igual a cero");
        } if (!tipoAsientoRepository.existsById(asientoDTO.getIdTipoAsiento())) {
            throw new Exception("El tipo de asiento con id " + asientoDTO.getIdTipoAsiento() + " no existe");
        } if (asientoDTO.getIdAvion() == null || asientoDTO.getIdAvion() <= 0) {
            throw new Exception("El id del avión no puede ser nulo o menor o igual a cero");
        } if (!avionRepository.existsById(asientoDTO.getIdAvion())) {
            throw new Exception("El avión con id " + asientoDTO.getIdAvion() + " no existe");
        } if (asientoDTO.getUbicacion() == null || asientoDTO.getUbicacion().isBlank() || asientoDTO.getUbicacion().trim().isEmpty()) {
            throw new Exception("La ubicación del asiento no puede ser nula o vacía");
        } if (!Pattern.matches(ConstantesUtility.PATTERN_SEAT_REGEX, asientoDTO.getUbicacion())) {
            throw new Exception("La ubicación del asiento no cumple con el patrón");
        } if (asientoDTO.getEstado() == null || asientoDTO.getEstado().isBlank() || asientoDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del asiento no puede ser nulo o vacío");
        }

        if (esGuardar) {
            if (asientoDTO.getIdAsiento() != null) {
                throw new Exception("El id del asiento no debe existir");
            }
        }

        if (!esGuardar) {
            if (!asientoRepository.existsById(asientoDTO.getIdAsiento())) {
                throw new Exception("El asiento con id " + asientoDTO.getIdAsiento() + " no existe");
            }
        }
    }

    @Override
    public AsientoDTO guardarAsiento(AsientoDTO asientoDTO) throws Exception {
        validarAsientoDTO(asientoDTO, true);

        return guardarOAcutalizarAsiento(asientoDTO);
    }

    @Override
    public List<AsientoDTO> obtenerAsientos() {
        return AsientoMapper.domainToDTOList(asientoRepository.findAll());
    }

    @Override
    public List<AsientoDTO> obtenerAsientosActivos() {
        return AsientoMapper.domainToDTOList(asientoRepository.findAllByEstado("A"));
    }

    @Override
    public AsientoDTO obtenerAsientoPorId(Integer id) throws Exception {
        if (!asientoRepository.existsById(id)) {
            throw new Exception("El asiento con id " + id + " no existe");
        }

        return AsientoMapper.domainToDTO(asientoRepository.getReferenceById(id));
    }

    @Override
    public AsientoDTO actualizarAsiento(AsientoDTO asientoDTO) throws Exception {
        validarAsientoDTO(asientoDTO, false);

        return guardarOAcutalizarAsiento(asientoDTO);
    }

    @Override
    public AsientoDTO eliminarAsiento(Integer id) throws Exception {
        AsientoDTO asientoSavedDTO = obtenerAsientoPorId(id);

        asientoSavedDTO.setEstado("I");

        return guardarOAcutalizarAsiento(asientoSavedDTO);
    }
}
