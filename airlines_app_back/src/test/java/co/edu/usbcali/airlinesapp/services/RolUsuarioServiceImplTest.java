package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.RolUsuarioDTO;
import co.edu.usbcali.airlinesapp.repository.RolUsuarioRepository;
import co.edu.usbcali.airlinesapp.services.implementation.RolUsuarioServiceImpl;

import co.edu.usbcali.airlinesapp.utility.RolUsuarioUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class RolUsuarioServiceImplTest {
    @InjectMocks
    private RolUsuarioServiceImpl rolUsuarioServiceImpl;

    @Mock
    private RolUsuarioRepository rolUsuarioRepository;

    @Test
    public void guardarRolUsuarioOk() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(false);
        given(rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO)).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);

        RolUsuarioDTO rolUsuarioSavedDTO = rolUsuarioServiceImpl.guardarRolUsuario(RolUsuarioUtilityTest.ROLUSUARIODTO);

        assertEquals(RolUsuarioUtilityTest.ID_UNO, rolUsuarioSavedDTO.getIdRolUsuario());
    }

    @Test
    public void guardarRolUsuarioNotOk() {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> rolUsuarioServiceImpl.guardarRolUsuario(RolUsuarioUtilityTest.ROLUSUARIODTO));
    }

    @Test
    public void obtenerRolUsuariosOk() {
        given(rolUsuarioRepository.findAll()).willReturn(RolUsuarioUtilityTest.ROLUSUARIOS);

        List<RolUsuarioDTO> rolUsuariosSavedDTO = rolUsuarioServiceImpl.obtenerRolUsuarios();

        assertEquals(RolUsuarioUtilityTest.ROLUSUARIOS_SIZE, rolUsuariosSavedDTO.size());
        assertEquals(RolUsuarioUtilityTest.DESCRIPCION_UNO, rolUsuariosSavedDTO.get(0).getDescripcion());
    }

    @Test
    public void obtenerRolUsuariosNotOk() {
        given(rolUsuarioRepository.findAll()).willReturn(RolUsuarioUtilityTest.ROLUSUARIOS_VACIO);

        List<RolUsuarioDTO> rolUsuariosSavedDTO = rolUsuarioServiceImpl.obtenerRolUsuarios();

        assertEquals(RolUsuarioUtilityTest.ROLUSUARIOS_VACIO_SIZE, rolUsuariosSavedDTO.size());
    }

    @Test
    public void obtenerRolUsuariosActivosOk() {
        given(rolUsuarioRepository.findAllByEstado("A")).willReturn(RolUsuarioUtilityTest.ROLUSUARIOS);

        List<RolUsuarioDTO> rolUsuariosSavedDTO = rolUsuarioServiceImpl.obtenerRolUsuariosActivos();

        assertEquals(RolUsuarioUtilityTest.ROLUSUARIOS_SIZE, rolUsuariosSavedDTO.size());
        assertEquals(RolUsuarioUtilityTest.DESCRIPCION_UNO, rolUsuariosSavedDTO.get(0).getDescripcion());
    }

    @Test
    public void obtenerRolUsuariosActivosNotOk() {
        given(rolUsuarioRepository.findAllByEstado("A")).willReturn(RolUsuarioUtilityTest.ROLUSUARIOS_VACIO);

        List<RolUsuarioDTO> rolUsuariosSavedDTO = rolUsuarioServiceImpl.obtenerRolUsuariosActivos();

        assertEquals(RolUsuarioUtilityTest.ROLUSUARIOS_VACIO_SIZE, rolUsuariosSavedDTO.size());
    }

    @Test
    public void obtenerRolUsuarioPorIdOk() throws Exception {
        rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO);

        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(rolUsuarioRepository.getReferenceById(RolUsuarioUtilityTest.ID_UNO)).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);

        RolUsuarioDTO rolUsuarioSavedDTO = rolUsuarioServiceImpl.obtenerRolUsuarioPorId(RolUsuarioUtilityTest.ID_UNO);

        assertEquals(RolUsuarioUtilityTest.ID_UNO, rolUsuarioSavedDTO.getIdRolUsuario());
    }

    @Test
    public void obtenerRolUsuarioPorIdNotOk() {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> rolUsuarioServiceImpl.obtenerRolUsuarioPorId(RolUsuarioUtilityTest.ID_UNO));
    }

    @Test
    public void actualizarRolUsuarioOk() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO)).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);

        RolUsuarioDTO rolUsuarioSavedDTO = rolUsuarioServiceImpl.actualizarRolUsuario(RolUsuarioUtilityTest.ROLUSUARIODTO);

        assertEquals(RolUsuarioUtilityTest.ID_UNO, rolUsuarioSavedDTO.getIdRolUsuario());
    }

    @Test
    public void actualizarRolUsuarioNotOk() {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> rolUsuarioServiceImpl.actualizarRolUsuario(RolUsuarioUtilityTest.ROLUSUARIODTO));
    }
}
