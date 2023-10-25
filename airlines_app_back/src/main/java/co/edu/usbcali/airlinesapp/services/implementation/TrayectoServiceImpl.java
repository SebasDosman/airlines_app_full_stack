package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;
import co.edu.usbcali.airlinesapp.domain.Avion;
import co.edu.usbcali.airlinesapp.domain.Trayecto;
import co.edu.usbcali.airlinesapp.domain.Vuelo;

import co.edu.usbcali.airlinesapp.dtos.TrayectoDTO;
import co.edu.usbcali.airlinesapp.mappers.TrayectoMapper;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.repository.AvionRepository;
import co.edu.usbcali.airlinesapp.repository.TrayectoRepository;
import co.edu.usbcali.airlinesapp.repository.VueloRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.TrayectoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TrayectoServiceImpl implements TrayectoService {
    private final TrayectoRepository trayectoRepository;
    private final AvionRepository avionRepository;
    private final AeropuertoRepository aeropuertoRepository;
    private final VueloRepository vueloRepository;

    public TrayectoServiceImpl(TrayectoRepository trayectoRepository, AvionRepository avionRepository, AeropuertoRepository aeropuertoRepository, VueloRepository vueloRepository) {
        this.trayectoRepository = trayectoRepository;
        this.avionRepository = avionRepository;
        this.aeropuertoRepository = aeropuertoRepository;
        this.vueloRepository = vueloRepository;
    }

    private TrayectoDTO guardarOActualizarTrayecto(TrayectoDTO trayectoDTO) {
        Trayecto trayecto = TrayectoMapper.dtoToDomain(trayectoDTO);

        Avion avion = avionRepository.getReferenceById(trayectoDTO.getIdAvion());
        Aeropuerto aeropuertoOrigen = aeropuertoRepository.getReferenceById(trayectoDTO.getIdAeropuertoOrigen());
        Aeropuerto aeropuertoDestino = aeropuertoRepository.getReferenceById(trayectoDTO.getIdAeropuertoDestino());
        Vuelo vuelo = vueloRepository.getReferenceById(trayectoDTO.getIdVuelo());

        trayecto.setAvion(avion);
        trayecto.setAeropuertoOrigen(aeropuertoOrigen);
        trayecto.setAeropuertoDestino(aeropuertoDestino);
        trayecto.setVuelo(vuelo);

        return TrayectoMapper.domainToDTO(trayectoRepository.save(trayecto));
    }

    private void validarTrayectoDTO(TrayectoDTO trayectoDTO, boolean esGuardar) throws Exception {
        if (trayectoDTO == null) {
            throw new Exception("El trayecto no puede ser nulo");
        } if (trayectoDTO.getIdAvion() == null || trayectoDTO.getIdAvion() <= 0) {
            throw new Exception("El id del avión no puede ser nulo o menor o igual a cero");
        } if (!avionRepository.existsById(trayectoDTO.getIdAvion())) {
            throw new Exception("El avión con id " + trayectoDTO.getIdAvion() + " no existe");
        } if (trayectoDTO.getIdAeropuertoOrigen() == null || trayectoDTO.getIdAeropuertoOrigen() <= 0) {
            throw new Exception("El id del aeropuerto de origen no puede ser nulo o menor o igual a cero");
        } if (!aeropuertoRepository.existsById(trayectoDTO.getIdAeropuertoOrigen())) {
            throw new Exception("El aeropuerto de origen con id " + trayectoDTO.getIdAeropuertoOrigen() + " no existe");
        } if (trayectoDTO.getIdAeropuertoDestino() == null || trayectoDTO.getIdAeropuertoDestino() <= 0) {
            throw new Exception("El id del aeropuerto de destino no puede ser nulo o menor o igual a cero");
        } if (!aeropuertoRepository.existsById(trayectoDTO.getIdAeropuertoDestino())) {
            throw new Exception("El aeropuerto de destino con id " + trayectoDTO.getIdAeropuertoDestino() + " no existe");
        } if (trayectoDTO.getIdAeropuertoOrigen().equals(trayectoDTO.getIdAeropuertoDestino())) {
            throw new Exception("El id del aeropuerto de origen no puede ser igual al id del aeropuerto de destino");
        } if (trayectoDTO.getHoraSalida() == null) {
            throw new Exception("La hora de salida del trayecto no puede ser nula");
        } if (trayectoDTO.getHoraLlegada() == null) {
            throw new Exception("La hora de llegada del trayecto no puede ser nula");
        } if (trayectoDTO.getHoraSalida().after(trayectoDTO.getHoraLlegada())) {
            throw new Exception("La hora de salida del trayecto no puede ser posterior a la hora de llegada");
        } if (trayectoDTO.getIdVuelo() == null || trayectoDTO.getIdVuelo() <= 0) {
            throw new Exception("El id del vuelo no puede ser nulo o menor o igual a cero");
        } if (!vueloRepository.existsById(trayectoDTO.getIdVuelo())) {
            throw new Exception("El vuelo con id " + trayectoDTO.getIdVuelo() + " no existe");
        } if (trayectoDTO.getEstado() == null || trayectoDTO.getEstado().isBlank() || trayectoDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del trayecto no puede ser nulo o vacío");
        }

//        if (MetodosUtility.esFechaActualOReciente(trayectoDTO.getHoraSalida())) {
//            throw new Exception("La hora de salida del trayecto no puede ser antigua a la fecha actual");
//        } if (MetodosUtility.esFechaActualOReciente(trayectoDTO.getHoraLlegada())) {
//            throw new Exception("La hora de llegada del trayecto no puede ser antigua a la fecha actual");
//        }

        if (esGuardar) {
            if (trayectoDTO.getIdTrayecto() != null) {
                throw new Exception("El id del trayecto no debe existir");
            }
        }

        if (!esGuardar) {
            if (!trayectoRepository.existsById(trayectoDTO.getIdTrayecto())) {
                throw new Exception("El trayecto con id " + trayectoDTO.getIdTrayecto() + " no existe");
            }
        }
    }

    @Override
    public TrayectoDTO guardarTrayecto(TrayectoDTO trayectoDTO) throws Exception {
        validarTrayectoDTO(trayectoDTO, true);

        return guardarOActualizarTrayecto(trayectoDTO);
    }

    @Override
    public List<TrayectoDTO> obtenerTrayectos() {
        return TrayectoMapper.domainToDTOList(trayectoRepository.findAll());
    }

    @Override
    public List<TrayectoDTO> obtenerTrayectosActivos() {
        return TrayectoMapper.domainToDTOList(trayectoRepository.findAllByEstado("A"));
    }

    @Override
    public TrayectoDTO obtenerTrayectoPorId(Integer id) throws Exception {
        if (!trayectoRepository.existsById(id)) {
            throw new Exception("El trayecto con id " + id + " no existe");
        }

        return TrayectoMapper.domainToDTO(trayectoRepository.getReferenceById(id));
    }

    @Override
    public TrayectoDTO actualizarTrayecto(TrayectoDTO trayectoDTO) throws Exception {
        validarTrayectoDTO(trayectoDTO, false);

        return guardarOActualizarTrayecto(trayectoDTO);
    }

    @Override
    public TrayectoDTO eliminarTrayecto(Integer id) throws Exception {
        TrayectoDTO trayectoSavedDTO = obtenerTrayectoPorId(id);

        trayectoSavedDTO.setEstado("I");

        return guardarTrayecto(trayectoSavedDTO);
    }

//    @Override
//    public AeropuertoDTO obtenerAeropuertoOrigenPorIdTrayecto(Integer idTrayecto) throws Exception {
//        if (!trayectoRepository.existsById(idTrayecto)) {
//            throw new Exception("El trayecto con id " + idTrayecto + " no existe");
//        }
//
//        return AeropuertoMapper.domainToDTO(trayectoRepository.findAeropuertoByIdTrayecto(idTrayecto));
//    }
}
