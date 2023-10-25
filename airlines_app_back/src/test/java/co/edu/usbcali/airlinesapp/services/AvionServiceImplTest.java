package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.AvionDTO;
import co.edu.usbcali.airlinesapp.repository.AvionRepository;
import co.edu.usbcali.airlinesapp.services.implementation.AvionServiceImpl;

import co.edu.usbcali.airlinesapp.utility.AvionUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AvionServiceImplTest {
    @InjectMocks
    private AvionServiceImpl avionServiceImpl;

    @Mock
    private AvionRepository avionRepository;

    @Test
    public void guardarAvionOk() throws Exception {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(false);
        given(avionRepository.save(AvionUtilityTest.AVION_UNO)).willReturn(AvionUtilityTest.AVION_UNO);

        AvionDTO avionSavedDTO = avionServiceImpl.guardarAvion(AvionUtilityTest.AVIONDTO);

        assertEquals(AvionUtilityTest.ID_UNO, avionSavedDTO.getIdAvion());
    }

    @Test
    public void guardarAvionNotOk() {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> avionServiceImpl.guardarAvion(AvionUtilityTest.AVIONDTO));
    }

    @Test
    public void obtenerAvionesOk() {
        given(avionRepository.findAll()).willReturn(AvionUtilityTest.AVIONES);

        List<AvionDTO> avionesSavedDTO = avionServiceImpl.obtenerAviones();

        assertEquals(AvionUtilityTest.AVIONES_SIZE, avionesSavedDTO.size());
        assertEquals(AvionUtilityTest.MODELO_UNO, avionesSavedDTO.get(0).getModelo());
    }

    @Test
    public void obtenerAvionesNotOk() {
        given(avionRepository.findAll()).willReturn(AvionUtilityTest.AVIONES_VACIO);

        List<AvionDTO> avionesDTO = avionServiceImpl.obtenerAviones();

        assertEquals(AvionUtilityTest.AVIONES_VACIO_SIZE, avionesDTO.size());
    }

    @Test
    public void obtenerAvionesActivosOk() {
        given(avionRepository.findAllByEstado("A")).willReturn(AvionUtilityTest.AVIONES);

        List<AvionDTO> avionesSavedDTO = avionServiceImpl.obtenerAvionesActivos();

        assertEquals(AvionUtilityTest.AVIONES_SIZE, avionesSavedDTO.size());
        assertEquals(AvionUtilityTest.MODELO_UNO, avionesSavedDTO.get(0).getModelo());
    }

    @Test
    public void obtenerAvionesActivosNotOk() {
        given(avionRepository.findAllByEstado("A")).willReturn(AvionUtilityTest.AVIONES_VACIO);

        List<AvionDTO> avionesSavedDTO = avionServiceImpl.obtenerAvionesActivos();

        assertEquals(AvionUtilityTest.AVIONES_VACIO_SIZE, avionesSavedDTO.size());
    }

    @Test
    public void obtenerAvionPorIdOk() throws Exception {
        avionRepository.save(AvionUtilityTest.AVION_UNO);

        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);
        given(avionRepository.getReferenceById(AvionUtilityTest.ID_UNO)).willReturn(AvionUtilityTest.AVION_UNO);

        AvionDTO avionDTO = avionServiceImpl.obtenerAvionPorId(AvionUtilityTest.ID_UNO);

        assertEquals(AvionUtilityTest.ID_UNO, avionDTO.getIdAvion());
    }

    @Test
    public void obtenerAvionPorIdNotOk() {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> avionServiceImpl.obtenerAvionPorId(AvionUtilityTest.ID_UNO));
    }

    @Test
    public void actualizarAvionOk() throws Exception {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);
        given(avionRepository.save(AvionUtilityTest.AVION_UNO)).willReturn(AvionUtilityTest.AVION_UNO);

        AvionDTO avionSavedDTO = avionServiceImpl.actualizarAvion(AvionUtilityTest.AVIONDTO);

        assertEquals(AvionUtilityTest.ID_UNO, avionSavedDTO.getIdAvion());
    }

    @Test
    public void actualizarAvionNotOk() {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> avionServiceImpl.actualizarAvion(AvionUtilityTest.AVIONDTO));
    }
}
