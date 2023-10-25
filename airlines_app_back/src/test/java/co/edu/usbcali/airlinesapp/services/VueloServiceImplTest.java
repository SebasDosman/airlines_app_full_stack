package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.VueloDTO;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.repository.VueloRepository;
import co.edu.usbcali.airlinesapp.services.implementation.VueloServiceImpl;

import co.edu.usbcali.airlinesapp.utility.AeropuertoUtilityTest;
import co.edu.usbcali.airlinesapp.utility.VueloUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class VueloServiceImplTest {
    @InjectMocks
    private VueloServiceImpl vueloServiceImpl;

    @Mock
    private VueloRepository vueloRepository;

    @Mock
    private AeropuertoRepository aeropuertoRepository;

    @Test
    public void guardarVueloOk() throws Exception {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_DOS)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_DOS)).willReturn(AeropuertoUtilityTest.AEROPUERTO_DOS);
        given(vueloRepository.existsById(VueloUtilityTest.ID_UNO)).willReturn(false);
        given(vueloRepository.save(VueloUtilityTest.VUELO_UNO)).willReturn(VueloUtilityTest.VUELO_UNO);

        VueloDTO vueloSavedDTO = vueloServiceImpl.guardarVuelo(VueloUtilityTest.VUELODTO);

        assertEquals(VueloUtilityTest.ID_UNO, vueloSavedDTO.getIdVuelo());
    }

    @Test
    public void guardarVueloNotOk() {
        given(vueloRepository.existsById(VueloUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> vueloServiceImpl.guardarVuelo(VueloUtilityTest.VUELODTO));
    }

    @Test
    public void obtenerVuelosOk() {
        given(vueloRepository.findAll()).willReturn(VueloUtilityTest.VUELOS);

        List<VueloDTO> vuelosSavedDTO = vueloServiceImpl.obtenerVuelos();

        assertEquals(VueloUtilityTest.VUELOS_SIZE, vuelosSavedDTO.size());
        assertEquals(VueloUtilityTest.PRECIO_UNO, vuelosSavedDTO.get(0).getPrecio());
    }

    @Test
    public void obtenerVuelosNotOk() {
        given(vueloRepository.findAll()).willReturn(VueloUtilityTest.VUELOS_VACIO);

        List<VueloDTO> vuelosSavedDTO = vueloServiceImpl.obtenerVuelos();

        assertEquals(VueloUtilityTest.VUELOS_VACIO_SIZE, vuelosSavedDTO.size());
    }

    @Test
    public void obtenerVuelosActivosOk() {
        given(vueloRepository.findAllByEstado("A")).willReturn(VueloUtilityTest.VUELOS);

        List<VueloDTO> vuelosSavedDTO = vueloServiceImpl.obtenerVuelosActivos();

        assertEquals(VueloUtilityTest.VUELOS_SIZE, vuelosSavedDTO.size());
        assertEquals(VueloUtilityTest.PRECIO_UNO, vuelosSavedDTO.get(0).getPrecio());
    }

    @Test
    public void obtenerVuelosActivosNotOk() {
        given(vueloRepository.findAllByEstado("A")).willReturn(VueloUtilityTest.VUELOS_VACIO);

        List<VueloDTO> vuelosSavedDTO = vueloServiceImpl.obtenerVuelosActivos();

        assertEquals(VueloUtilityTest.VUELOS_VACIO_SIZE, vuelosSavedDTO.size());
    }

    @Test
    public void obtenerVueloPorIdOk() throws Exception {
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_UNO);
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_DOS);
        vueloRepository.save(VueloUtilityTest.VUELO_UNO);

        given(vueloRepository.existsById(VueloUtilityTest.ID_UNO)).willReturn(true);
        given(vueloRepository.getReferenceById(VueloUtilityTest.ID_UNO)).willReturn(VueloUtilityTest.VUELO_UNO);

        VueloDTO vueloSavedDTO = vueloServiceImpl.obtenerVueloPorId(VueloUtilityTest.ID_UNO);

        assertEquals(VueloUtilityTest.ID_UNO, vueloSavedDTO.getIdVuelo());
    }

    @Test
    public void obtenerVueloPorIdNotOk() {
        given(vueloRepository.existsById(VueloUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> vueloServiceImpl.obtenerVueloPorId(VueloUtilityTest.ID_UNO));
    }

    @Test
    public void actualizarVueloOk() throws Exception {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_DOS)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_DOS)).willReturn(AeropuertoUtilityTest.AEROPUERTO_DOS);
        given(vueloRepository.existsById(VueloUtilityTest.ID_UNO)).willReturn(true);
        given(vueloRepository.save(VueloUtilityTest.VUELO_UNO)).willReturn(VueloUtilityTest.VUELO_UNO);

        VueloDTO vueloSavedDTO = vueloServiceImpl.actualizarVuelo(VueloUtilityTest.VUELODTO);

        assertEquals(VueloUtilityTest.ID_UNO, vueloSavedDTO.getIdVuelo());
    }

    @Test
    public void actualizarVueloNotOk() {
        given(vueloRepository.existsById(VueloUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> vueloServiceImpl.actualizarVuelo(VueloUtilityTest.VUELODTO));
    }
}
