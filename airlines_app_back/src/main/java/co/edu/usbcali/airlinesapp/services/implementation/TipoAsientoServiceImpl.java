package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.TipoAsiento;
import co.edu.usbcali.airlinesapp.dtos.TipoAsientoDTO;
import co.edu.usbcali.airlinesapp.mappers.TipoAsientoMapper;
import co.edu.usbcali.airlinesapp.repository.TipoAsientoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.TipoAsientoService;

import co.edu.usbcali.airlinesapp.utility.ConstantesUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class TipoAsientoServiceImpl implements TipoAsientoService {
    private final TipoAsientoRepository tipoAsientoRepository;

    public TipoAsientoServiceImpl(TipoAsientoRepository tipoAsientoRepository) {
        this.tipoAsientoRepository = tipoAsientoRepository;
    }

    private TipoAsientoDTO guardarOActualizarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception {
        TipoAsiento tipoAsiento = TipoAsientoMapper.dtoToDomain(tipoAsientoDTO);

        return TipoAsientoMapper.domainToDTO(tipoAsientoRepository.save(tipoAsiento));
    }

    private void validarTipoAsiento(TipoAsientoDTO tipoAsientoDTO, boolean esGuardar) throws Exception {
        if (tipoAsientoDTO == null) {
            throw new Exception("El tipo de asiento no puede ser nulo");
        } if (tipoAsientoDTO.getDescripcion() == null || tipoAsientoDTO.getDescripcion().isBlank() || tipoAsientoDTO.getDescripcion().trim().isEmpty()) {
            throw new Exception("La descripción del tipo de asiento no puede ser nula o vacía");
        } if (!Pattern.matches(ConstantesUtility.PATTERN_NAME_REGEX, tipoAsientoDTO.getDescripcion())) {
            throw new Exception("La descripción del tipo de asiento no puede contener números o caracteres especiales");
        } if (tipoAsientoDTO.getEstado() == null || tipoAsientoDTO.getEstado().isBlank() || tipoAsientoDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del tipo de asiento no puede ser nulo o vacío");
        }

        if (esGuardar) {
            if (tipoAsientoDTO.getIdTipoAsiento() != null) {
                throw new Exception("El id del tipo de asiento no debe existir");
            }
        }

        if (!esGuardar) {
            if (!tipoAsientoRepository.existsById(tipoAsientoDTO.getIdTipoAsiento())) {
                throw new Exception("El tipo de asiento con id " + tipoAsientoDTO.getIdTipoAsiento() + " no existe");
            }
        }
    }

    @Override
    public TipoAsientoDTO guardarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception {
        validarTipoAsiento(tipoAsientoDTO, true);

        return guardarOActualizarTipoAsiento(tipoAsientoDTO);
    }

    @Override
    public List<TipoAsientoDTO> obtenerTipoAsientos() {
        return TipoAsientoMapper.domainToDTOList(tipoAsientoRepository.findAll());
    }

    @Override
    public List<TipoAsientoDTO> obtenerTipoAsientosActivos() {
        return TipoAsientoMapper.domainToDTOList(tipoAsientoRepository.findAllByEstado("A"));
    }

    @Override
    public TipoAsientoDTO obtenerTipoAsientoPorId(Integer id) throws Exception {
        if (!tipoAsientoRepository.existsById(id)) {
            throw new Exception("El tipo de asiento con id " + id + " no existe");
        }

        return TipoAsientoMapper.domainToDTO(tipoAsientoRepository.getReferenceById(id));

    }

    @Override
    public TipoAsientoDTO actualizarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception {
        validarTipoAsiento(tipoAsientoDTO, false);

        return guardarOActualizarTipoAsiento(tipoAsientoDTO);
    }

    @Override
    public TipoAsientoDTO eliminarTipoAsiento(Integer id) throws Exception {
        TipoAsientoDTO tipoAsientoSavedDTO = obtenerTipoAsientoPorId(id);

        tipoAsientoSavedDTO.setEstado("I");

        return guardarTipoAsiento(tipoAsientoSavedDTO);
    }
}
