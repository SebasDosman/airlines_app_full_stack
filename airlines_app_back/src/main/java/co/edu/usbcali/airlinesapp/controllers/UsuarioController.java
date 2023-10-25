package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.dtos.MensajeDTO;
import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;
import co.edu.usbcali.airlinesapp.services.interfaces.UsuarioService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@Slf4j
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(path = "/guardar-usuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return new ResponseEntity(usuarioService.guardarUsuario(usuarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtener-usuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() {
        return new ResponseEntity(usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/obtener-usuariosActivos")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosActivos() {
        return new ResponseEntity(usuarioService.obtenerUsuariosActivos(), HttpStatus.OK);
    }

    @GetMapping("/obtener-usuarioId/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable("idUsuario") Integer idUsuario) {
        try {
            return new ResponseEntity(usuarioService.obtenerUsuarioPorId(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtener-usuarioCedula/{cedula}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorCedula(@PathVariable("cedula") String cedula) {
        try {
            return new ResponseEntity(usuarioService.obtenerUsuarioPorCedula(cedula), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/actualizar-usuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return new ResponseEntity(usuarioService.actualizarUsuario(usuarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/eliminar-usuario/{idUsuario}")
    public ResponseEntity eliminarUsuario(@PathVariable("idUsuario") Integer idUsuario) {
        try {
            return new ResponseEntity(usuarioService.eliminarUsuario(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
