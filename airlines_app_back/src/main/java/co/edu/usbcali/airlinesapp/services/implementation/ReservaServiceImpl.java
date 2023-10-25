package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Asiento;
import co.edu.usbcali.airlinesapp.domain.Reserva;
import co.edu.usbcali.airlinesapp.domain.Usuario;
import co.edu.usbcali.airlinesapp.domain.Vuelo;
import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;
import co.edu.usbcali.airlinesapp.mappers.ReservaMapper;
import co.edu.usbcali.airlinesapp.repository.AsientoRepository;
import co.edu.usbcali.airlinesapp.repository.ReservaRepository;
import co.edu.usbcali.airlinesapp.repository.UsuarioRepository;
import co.edu.usbcali.airlinesapp.repository.VueloRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.ReservaService;

import co.edu.usbcali.airlinesapp.services.interfaces.UsuarioService;
import co.edu.usbcali.airlinesapp.services.interfaces.VueloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final VueloRepository vueloRepository;
    private final VueloService vueloService;
    private final AsientoRepository asientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public ReservaServiceImpl(ReservaRepository reservaRepository, VueloRepository vueloRepository, VueloService vueloService, AsientoRepository asientoRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.reservaRepository = reservaRepository;
        this.vueloRepository = vueloRepository;
        this.vueloService = vueloService;
        this.asientoRepository = asientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    private ReservaDTO guardarOActualizarReserva(ReservaDTO reservaDTO) {
        Reserva reserva = ReservaMapper.dtoToDomain(reservaDTO);

        Vuelo vuelo = vueloRepository.getReferenceById(reservaDTO.getIdVuelo());
        Asiento asiento = asientoRepository.getReferenceById(reservaDTO.getIdAsiento());
        Usuario usuario = usuarioRepository.getReferenceById(reservaDTO.getIdUsuario());

        reserva.setVuelo(vuelo);
        reserva.setAsiento(asiento);
        reserva.setUsuario(usuario);

        return ReservaMapper.domainToDTO(reservaRepository.save(reserva));
    }

    private void validarReservaDTO(ReservaDTO reservaDTO, boolean esGuardar) throws Exception {
        if (reservaDTO == null) {
            throw new Exception("La reserva no puede ser nula");
        } if (reservaDTO.getIdVuelo() == null || reservaDTO.getIdVuelo() <= 0) {
            throw new Exception("El vuelo de la reserva no puede ser nulo o menor o igual a cero");
        } if (!vueloRepository.existsById(reservaDTO.getIdVuelo())) {
            throw new Exception("El vuelo con id " + reservaDTO.getIdVuelo() + " no existe");
        } if (reservaDTO.getIdAsiento() == null || reservaDTO.getIdAsiento() <= 0) {
            throw new Exception("El asiento de la reserva no puede ser nulo o menor o igual a cero");
        } if (!asientoRepository.existsById(reservaDTO.getIdAsiento())) {
            throw new Exception("El asiento con id " + reservaDTO.getIdAsiento() + " no existe");
        } if (reservaDTO.getIdUsuario() == null || reservaDTO.getIdUsuario() <= 0) {
            throw new Exception("El usuario de la reserva no puede ser nulo o menor o igual a cero");
        } if (!usuarioRepository.existsById(reservaDTO.getIdUsuario())) {
            throw new Exception("El usuario con id " + reservaDTO.getIdUsuario() + " no existe");
        } if (reservaDTO.getPrecioTotal() <= 0) {
            throw new Exception("El precio total de la reserva no puede ser menor o igual a cero");
        } if (reservaDTO.getEstadoPago() == null || reservaDTO.getEstadoPago().isBlank() || reservaDTO.getEstadoPago().trim().isEmpty()) {
            throw new Exception("El estado de pago de la reserva no puede ser nulo o vacío");
        } if (reservaDTO.getFecha() == null) {
            throw new Exception("La fecha de la reserva no puede ser nula");
        } if (reservaDTO.getEstado() == null || reservaDTO.getEstado().isBlank() || reservaDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado de la reserva no puede ser nulo o vacío");
        }

//        if (MetodosUtility.esFechaActualOReciente(reservaDTO.getFecha())) {
//            throw new Exception("La fecha de la reserva no puede ser antigua a la fecha actual");
//        }

        if (esGuardar) {
            if (reservaDTO.getIdReserva() != null) {
                throw new Exception("El id de la reserva no debe existir");
            }
        }

        if (!esGuardar) {
            if (!reservaRepository.existsById(reservaDTO.getIdReserva())) {
                throw new Exception("La reserva con id " + reservaDTO.getIdReserva() + " no existe");
            }
        }
    }

    @Override
    public ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception {
        validarReservaDTO(reservaDTO, true);

        return guardarOActualizarReserva(reservaDTO);
    }

    @Override
    public List<ReservaDTO> obtenerReservas() {
        return ReservaMapper.domainToDTOList(reservaRepository.findAll());
    }

    @Override
    public List<ReservaDTO> obtenerReservasActivas() {
        return ReservaMapper.domainToDTOList(reservaRepository.findAllByEstado("A"));
    }

    @Override
    public ReservaDTO obtenerReservaPorId(Integer id) throws Exception {
        if (!reservaRepository.existsById(id)) {
            throw new Exception("La reserva con id " + id + " no existe");
        }

        return ReservaMapper.domainToDTO(reservaRepository.getReferenceById(id));
    }

    @Override
    public List<ReservaDTO> obtenerReservasPorIdVuelo(Integer idVuelo) throws Exception {
        vueloService.obtenerVueloPorId(idVuelo);

        return ReservaMapper.domainToDTOList(reservaRepository.findAllByVuelo_IdVuelo(idVuelo));
    }

    @Override
    public List<ReservaDTO> obtenerReservasPorCedula(String cedula) throws Exception {
        usuarioService.obtenerUsuarioPorCedula(cedula);

        return ReservaMapper.domainToDTOList(reservaRepository.findAllByUsuario_Cedula(cedula));
    }

    @Override
    public ReservaDTO actualizarReserva(ReservaDTO reservaDTO) throws Exception {
        validarReservaDTO(reservaDTO, false);

        return guardarOActualizarReserva(reservaDTO);
    }

    @Override
    public ReservaDTO eliminarReserva(Integer id) throws Exception {
        ReservaDTO reservaSavedDTO = obtenerReservaPorId(id);

        reservaSavedDTO.setEstado("I");

        return guardarReserva(reservaSavedDTO);
    }
}
