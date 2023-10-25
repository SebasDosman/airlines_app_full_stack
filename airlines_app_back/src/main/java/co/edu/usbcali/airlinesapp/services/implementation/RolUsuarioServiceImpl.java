package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.RolUsuario;
import co.edu.usbcali.airlinesapp.dtos.RolUsuarioDTO;
import co.edu.usbcali.airlinesapp.mappers.RolUsuarioMapper;
import co.edu.usbcali.airlinesapp.repository.RolUsuarioRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.RolUsuarioService;

import co.edu.usbcali.airlinesapp.utility.ConstantesUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class RolUsuarioServiceImpl implements RolUsuarioService {
    private final RolUsuarioRepository rolUsuarioRepository;

    public RolUsuarioServiceImpl(RolUsuarioRepository rolUsuarioRepository) {
        this.rolUsuarioRepository = rolUsuarioRepository;
    }

    private RolUsuarioDTO guardarOActualizarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception {
        RolUsuario rolUsuario = RolUsuarioMapper.dtoToDomain(rolUsuarioDTO);

        return RolUsuarioMapper.domainToDTO(rolUsuarioRepository.save(rolUsuario));
    }

    private void validarRolUsuarioDTO(RolUsuarioDTO rolUsuarioDTO, boolean esGuardar) throws Exception {
        if (rolUsuarioDTO == null) {
            throw new Exception("El rol de usuario no puede ser nulo");
        } if (rolUsuarioDTO.getDescripcion() == null || rolUsuarioDTO.getDescripcion().isBlank() || rolUsuarioDTO.getDescripcion().trim().isEmpty()) {
            throw new Exception("La descripción del rol de usuario no puede ser nula o vacía");
        } if (!Pattern.matches(ConstantesUtility.PATTERN_NAME_REGEX, rolUsuarioDTO.getDescripcion())) {
            throw new Exception("La descripción del rol de usuario no puede contener números o caracteres especiales");
        } if (rolUsuarioDTO.getEstado() == null || rolUsuarioDTO.getEstado().isBlank() || rolUsuarioDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del rol de usuario no puede ser nulo o vacío");
        }

        if (esGuardar) {
            if (rolUsuarioDTO.getIdRolUsuario() != null) {
                throw new Exception("El id del rol de usuario no debe existir");
            }
        }

        if (!esGuardar) {
            if (!rolUsuarioRepository.existsById(rolUsuarioDTO.getIdRolUsuario())) {
                throw new Exception("El rol de usuario con id " + rolUsuarioDTO.getIdRolUsuario() + " no existe");
            }
        }
    }

    @Override
    public RolUsuarioDTO guardarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception {
        validarRolUsuarioDTO(rolUsuarioDTO, true);

        return guardarOActualizarRolUsuario(rolUsuarioDTO);
    }

    @Override
    public List<RolUsuarioDTO> obtenerRolUsuarios() {
        return RolUsuarioMapper.domainToDTOList(rolUsuarioRepository.findAll());
    }

    @Override
    public List<RolUsuarioDTO> obtenerRolUsuariosActivos() {
        return RolUsuarioMapper.domainToDTOList(rolUsuarioRepository.findAllByEstado("A"));
    }

    @Override
    public RolUsuarioDTO obtenerRolUsuarioPorId(Integer id) throws Exception {
        if (!rolUsuarioRepository.existsById(id)) {
            throw new Exception("El rol de usuario con id " + id + " no existe");
        }

        return RolUsuarioMapper.domainToDTO(rolUsuarioRepository.getReferenceById(id));
    }

    @Override
    public RolUsuarioDTO actualizarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception {
        validarRolUsuarioDTO(rolUsuarioDTO, false);

        return guardarOActualizarRolUsuario(rolUsuarioDTO);
    }

    @Override
    public RolUsuarioDTO eliminarRolUsuario(Integer id) throws Exception {
        RolUsuarioDTO rolUsuarioSavedDTO = obtenerRolUsuarioPorId(id);

        rolUsuarioSavedDTO.setEstado("I");

        return guardarRolUsuario(rolUsuarioSavedDTO);
    }
}
