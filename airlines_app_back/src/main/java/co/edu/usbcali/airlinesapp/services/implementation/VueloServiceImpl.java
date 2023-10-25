package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;
import co.edu.usbcali.airlinesapp.domain.Vuelo;
import co.edu.usbcali.airlinesapp.dtos.VueloDTO;
import co.edu.usbcali.airlinesapp.mappers.VueloMapper;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.repository.VueloRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.VueloService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VueloServiceImpl implements VueloService {
    private final VueloRepository vueloRepository;
    private final AeropuertoRepository aeropuertoRepository;

    public VueloServiceImpl(VueloRepository vueloRepository, AeropuertoRepository aeropuertoRepository) {
        this.vueloRepository = vueloRepository;
        this.aeropuertoRepository = aeropuertoRepository;
    }

    private VueloDTO guardarOActualizarVuelo(VueloDTO vueloDTO) {
        Vuelo vuelo = VueloMapper.dtoToDomain(vueloDTO);

        Aeropuerto aeropuertoOrigen = aeropuertoRepository.getReferenceById(vueloDTO.getIdAeropuertoOrigen());
        Aeropuerto aeropuertoDestino = aeropuertoRepository.getReferenceById(vueloDTO.getIdAeropuertoDestino());

        vuelo.setAeropuertoOrigen(aeropuertoOrigen);
        vuelo.setAeropuertoDestino(aeropuertoDestino);

        return VueloMapper.domainToDTO(vueloRepository.save(vuelo));
    }

    private void validarVueloDTO(VueloDTO vueloDTO, boolean esGuardar) throws Exception {
        if (vueloDTO == null) {
            throw new Exception("El vuelo no puede ser nulo");
        } if (vueloDTO.getIdAeropuertoOrigen() == null || vueloDTO.getIdAeropuertoOrigen() <= 0) {
            throw new Exception("El id del aeropuerto de origen no puede ser nulo o menor o igual a cero");
        } if (!vueloRepository.existsById(vueloDTO.getIdAeropuertoOrigen())) {
            throw new Exception("El id del aeropuerto de origen no existe");
        } if (vueloDTO.getIdAeropuertoDestino() == null || vueloDTO.getIdAeropuertoDestino() <= 0) {
            throw new Exception("El id del aeropuerto de destino no puede ser nulo o menor o igual a cero");
        } if (!vueloRepository.existsById(vueloDTO.getIdAeropuertoDestino())) {
            throw new Exception("El id del aeropuerto de destino no existe");
        } if (vueloDTO.getIdAeropuertoOrigen().equals(vueloDTO.getIdAeropuertoDestino())) {
            throw new Exception("El id del aeropuerto de origen no puede ser igual al id del aeropuerto de destino");
        } if (vueloDTO.getPrecio() <= 0) {
            throw new Exception("El precio del vuelo no puede ser menor o igual a cero");
        } if (vueloDTO.getHoraSalida() == null) {
            throw new Exception("La hora de salida del vuelo no puede ser nula");
        } if (vueloDTO.getHoraLlegada() == null) {
            throw new Exception("La hora de llegada del vuelo no puede ser nula");
        } if (vueloDTO.getHoraSalida().after(vueloDTO.getHoraLlegada())) {
            throw new Exception("La hora de salida del vuelo no puede ser posterior a la hora de llegada");
        } if (vueloDTO.getPrecioAsientoVip() <= 0) {
            throw new Exception("El precio del asiento vip no puede ser menor o igual a cero");
        } if (vueloDTO.getPrecioAsientoNormal() <= 0) {
            throw new Exception("El precio del asiento normal no puede ser menor o igual a cero");
        } if (vueloDTO.getPrecioAsientoBasico() <= 0) {
            throw new Exception("El precio del asiento básico no puede ser menor o igual a cero");
        } if (vueloDTO.getEstado() == null || vueloDTO.getEstado().isBlank() || vueloDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del vuelo no puede ser nulo o vacío");
        }

//        if (MetodosUtility.esFechaActualOReciente(vueloDTO.getHoraSalida())) {
//            throw new Exception("La hora de salida del vuelo no puede ser antigua a la fecha actual");
//        } if (MetodosUtility.esFechaActualOReciente(vueloDTO.getHoraLlegada())) {
//            throw new Exception("La hora de llegada del vuelo no puede ser antigua a la fecha actual");
//        }

        if (esGuardar) {
            if (vueloDTO.getIdVuelo() != null) {
                throw new Exception("El id del vuelo no debe existir");
            }
        }

        if (!esGuardar) {
            if (!vueloRepository.existsById(vueloDTO.getIdVuelo())) {
                throw new Exception("El vuelo con id " + vueloDTO.getIdVuelo() + " no existe");
            }
        }
    }

    @Override
    public VueloDTO guardarVuelo(VueloDTO vueloDTO) throws Exception {
        validarVueloDTO(vueloDTO, true);

        return guardarOActualizarVuelo(vueloDTO);
    }

    @Override
    public List<VueloDTO> obtenerVuelos() {
        return VueloMapper.domainToDTOList(vueloRepository.findAll());
    }

    @Override
    public List<VueloDTO> obtenerVuelosActivos() {
        return VueloMapper.domainToDTOList(vueloRepository.findAllByEstado("A"));
    }

    @Override
    public VueloDTO obtenerVueloPorId(Integer id) throws Exception {
        if (!vueloRepository.existsById(id)) {
            throw new Exception("El vuelo con id " + id + " no existe");
        }

        return VueloMapper.domainToDTO(vueloRepository.getReferenceById(id));
    }

    @Override
    public VueloDTO actualizarVuelo(VueloDTO vueloDTO) throws Exception {
        validarVueloDTO(vueloDTO, false);

        return guardarOActualizarVuelo(vueloDTO);
    }

    @Override
    public VueloDTO eliminarVuelo(Integer id) throws Exception {
        VueloDTO vueloSavedDTO = obtenerVueloPorId(id);

        vueloSavedDTO.setEstado("I");

        return guardarVuelo(vueloSavedDTO);
    }
}
