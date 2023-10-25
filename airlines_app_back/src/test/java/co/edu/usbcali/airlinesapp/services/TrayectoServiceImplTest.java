package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.TrayectoDTO;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.repository.AvionRepository;
import co.edu.usbcali.airlinesapp.repository.TrayectoRepository;
import co.edu.usbcali.airlinesapp.repository.VueloRepository;
import co.edu.usbcali.airlinesapp.services.implementation.TrayectoServiceImpl;

import co.edu.usbcali.airlinesapp.utility.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class TrayectoServiceImplTest {
    @InjectMocks
    private TrayectoServiceImpl trayectoServiceImpl;

    @Mock
    private TrayectoRepository trayectoRepository;

    @Mock
    private AvionRepository avionRepository;

    @Mock
    private AeropuertoRepository aeropuertoRepository;

    @Mock
    private VueloRepository vueloRepository;

    @Test
    public void guardarTrayectoOk() throws Exception {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);
        given(avionRepository.getReferenceById(AvionUtilityTest.ID_UNO)).willReturn(AvionUtilityTest.AVION_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_DOS)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_DOS)).willReturn(AeropuertoUtilityTest.AEROPUERTO_DOS);
        given(vueloRepository.existsById(VueloUtilityTest.ID_UNO)).willReturn(true);
        given(vueloRepository.getReferenceById(VueloUtilityTest.ID_UNO)).willReturn(VueloUtilityTest.VUELO_UNO);
        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(false);
        given(trayectoRepository.save(TrayectoUtilityTest.TRAYECTO_UNO)).willReturn(TrayectoUtilityTest.TRAYECTO_UNO);

        TrayectoDTO trayectoSavedDTO = trayectoServiceImpl.guardarTrayecto(TrayectoUtilityTest.TRAYECTODTO);

        assertEquals(TrayectoUtilityTest.ID_UNO, trayectoSavedDTO.getIdTrayecto());
    }

    @Test
    public void guardarTrayectoNotOk() {
        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> trayectoServiceImpl.guardarTrayecto(TrayectoUtilityTest.TRAYECTODTO));
    }

    @Test
    public void obtenerTrayectosOk() {
        given(trayectoRepository.findAll()).willReturn(TrayectoUtilityTest.TRAYECTOS);

        List<TrayectoDTO> trayectosSavedDTO = trayectoServiceImpl.obtenerTrayectos();

        assertEquals(TrayectoUtilityTest.TRAYECTOS_SIZE, trayectosSavedDTO.size());
        assertEquals(TrayectoUtilityTest.ID_UNO, trayectosSavedDTO.get(0).getIdTrayecto());
    }

    @Test
    public void obtenerTrayectosNotOk() {
        given(trayectoRepository.findAll()).willReturn(TrayectoUtilityTest.TRAYECTOS_VACIO);

        List<TrayectoDTO> trayectosSavedDTO = trayectoServiceImpl.obtenerTrayectos();

        assertEquals(TrayectoUtilityTest.TRAYECTOS_VACIO_SIZE, trayectosSavedDTO.size());
    }

    @Test
    public void obtenerTrayectosActivosOk() {
        given(trayectoRepository.findAllByEstado("A")).willReturn(TrayectoUtilityTest.TRAYECTOS);

        List<TrayectoDTO> trayectosSavedDTO = trayectoServiceImpl.obtenerTrayectosActivos();

        assertEquals(TrayectoUtilityTest.TRAYECTOS_SIZE, trayectosSavedDTO.size());
        assertEquals(TrayectoUtilityTest.ID_UNO, trayectosSavedDTO.get(0).getIdTrayecto());
    }

    @Test
    public void obtenerTrayectosActivosNotOk() {
        given(trayectoRepository.findAllByEstado("A")).willReturn(TrayectoUtilityTest.TRAYECTOS_VACIO);

        List<TrayectoDTO> trayectosSavedDTO = trayectoServiceImpl.obtenerTrayectosActivos();

        assertEquals(TrayectoUtilityTest.TRAYECTOS_VACIO_SIZE, trayectosSavedDTO.size());
    }

    @Test
    public void obtenerTrayectosPorIdOk() throws Exception {
        avionRepository.save(AvionUtilityTest.AVION_UNO);
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_UNO);
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_DOS);
        vueloRepository.save(VueloUtilityTest.VUELO_UNO);
        trayectoRepository.save(TrayectoUtilityTest.TRAYECTO_UNO);

        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(true);
        given(trayectoRepository.getReferenceById(TrayectoUtilityTest.ID_UNO)).willReturn(TrayectoUtilityTest.TRAYECTO_UNO);

        TrayectoDTO trayectoSavedDTO = trayectoServiceImpl.obtenerTrayectoPorId(TrayectoUtilityTest.ID_UNO);

        assertEquals(TrayectoUtilityTest.ID_UNO, trayectoSavedDTO.getIdTrayecto());
    }

    @Test
    public void obtenerTrayectosPorIdNotOk() {
        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> trayectoServiceImpl.obtenerTrayectoPorId(TrayectoUtilityTest.ID_UNO));
    }

    @Test
    public void actualizarTrayectoOk() throws Exception {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);
        given(avionRepository.getReferenceById(AvionUtilityTest.ID_UNO)).willReturn(AvionUtilityTest.AVION_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_DOS)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_DOS)).willReturn(AeropuertoUtilityTest.AEROPUERTO_DOS);
        given(vueloRepository.existsById(VueloUtilityTest.ID_UNO)).willReturn(true);
        given(vueloRepository.getReferenceById(VueloUtilityTest.ID_UNO)).willReturn(VueloUtilityTest.VUELO_UNO);
        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(true);
        given(trayectoRepository.save(TrayectoUtilityTest.TRAYECTO_UNO)).willReturn(TrayectoUtilityTest.TRAYECTO_UNO);

        TrayectoDTO trayectoSavedDTO = trayectoServiceImpl.actualizarTrayecto(TrayectoUtilityTest.TRAYECTODTO);

        assertEquals(TrayectoUtilityTest.ID_UNO, trayectoSavedDTO.getIdTrayecto());
    }

    @Test
    public void actualizarTrayectoNotOk() {
        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> trayectoServiceImpl.actualizarTrayecto(TrayectoUtilityTest.TRAYECTODTO));
    }
}
