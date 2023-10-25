package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;
import co.edu.usbcali.airlinesapp.repository.RolUsuarioRepository;
import co.edu.usbcali.airlinesapp.repository.UsuarioRepository;
import co.edu.usbcali.airlinesapp.services.implementation.UsuarioServiceImpl;

import co.edu.usbcali.airlinesapp.utility.RolUsuarioUtilityTest;
import co.edu.usbcali.airlinesapp.utility.UsuarioUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class UsuarioServiceImplTest {
    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolUsuarioRepository rolUsuarioRepository;

    @Test
    public void guardarUsuarioOk() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(rolUsuarioRepository.getReferenceById(RolUsuarioUtilityTest.ID_UNO)).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(false);
        given(usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.guardarUsuario(UsuarioUtilityTest.USUARIODTO);

        assertEquals(UsuarioUtilityTest.ID_UNO, usuarioSavedDTO.getIdUsuario());
    }

    @Test
    public void guardarUsuarioNotOk() {
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> usuarioServiceImpl.guardarUsuario(UsuarioUtilityTest.USUARIODTO));
    }

    @Test
    public void obtenerUsuariosOk() {
        given(usuarioRepository.findAll()).willReturn(UsuarioUtilityTest.USUARIOS);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuarios();

        assertEquals(UsuarioUtilityTest.USUARIOS_SIZE, usuariosSavedDTO.size());
        assertEquals(UsuarioUtilityTest.CEDULA_UNO, usuariosSavedDTO.get(0).getCedula());
    }

    @Test
    public void obtenerUsuariosNotOk() {
        given(usuarioRepository.findAll()).willReturn(UsuarioUtilityTest.USUARIOS_VACIO);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuarios();

        assertEquals(UsuarioUtilityTest.USUARIOS_VACIO_SIZE, usuariosSavedDTO.size());
    }

    @Test
    public void obtenerUsuariosActivosOk() {
        given(usuarioRepository.findAllByEstado("A")).willReturn(UsuarioUtilityTest.USUARIOS);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuariosActivos();

        assertEquals(UsuarioUtilityTest.USUARIOS_SIZE, usuariosSavedDTO.size());
        assertEquals(UsuarioUtilityTest.CEDULA_UNO, usuariosSavedDTO.get(0).getCedula());
    }

    @Test
    public void obtenerUsuariosActivosNotOk() {
        given(usuarioRepository.findAllByEstado("A")).willReturn(UsuarioUtilityTest.USUARIOS_VACIO);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuariosActivos();

        assertEquals(UsuarioUtilityTest.USUARIOS_VACIO_SIZE, usuariosSavedDTO.size());
    }

    @Test
    public void obtenerUsuarioPorIdOk() throws Exception {
        rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO);

        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(usuarioRepository.getReferenceById(UsuarioUtilityTest.ID_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.obtenerUsuarioPorId(UsuarioUtilityTest.ID_UNO);

        assertEquals(UsuarioUtilityTest.ID_UNO, usuarioSavedDTO.getIdUsuario());
    }

    @Test
    public void obtenerUsuarioPorIdNotOk() {
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> usuarioServiceImpl.obtenerUsuarioPorId(UsuarioUtilityTest.ID_UNO));
    }

    @Test
    public void obtenerUsuarioPorCedulaOk() throws Exception {
        rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO);

        given(usuarioRepository.existsByCedula(UsuarioUtilityTest.CEDULA_UNO)).willReturn(true);
        given(usuarioRepository.getReferenceByCedula(UsuarioUtilityTest.CEDULA_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.obtenerUsuarioPorCedula(UsuarioUtilityTest.CEDULA_UNO);

        assertEquals(UsuarioUtilityTest.CEDULA_UNO, usuarioSavedDTO.getCedula());
    }

    @Test
    public void obtenerUsuarioPorCedulaNotOk() throws Exception {
        given(usuarioRepository.existsByCedula(UsuarioUtilityTest.CEDULA_UNO)).willReturn(false);

        assertNull(usuarioServiceImpl.obtenerUsuarioPorCedula(UsuarioUtilityTest.CEDULA_UNO));
    }

    @Test
    public void actualizarUsuarioOk() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(rolUsuarioRepository.getReferenceById(RolUsuarioUtilityTest.ID_UNO)).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.actualizarUsuario(UsuarioUtilityTest.USUARIODTO);

        assertEquals(UsuarioUtilityTest.ID_UNO, usuarioSavedDTO.getIdUsuario());
    }

    @Test
    public void actualizarUsuarioNotOk() {
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> usuarioServiceImpl.actualizarUsuario(UsuarioUtilityTest.USUARIODTO));
    }
}
