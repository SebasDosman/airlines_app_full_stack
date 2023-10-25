package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.dtos.MensajeDTO;
import co.edu.usbcali.airlinesapp.dtos.TipoAsientoDTO;
import co.edu.usbcali.airlinesapp.services.interfaces.TipoAsientoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoAsiento")
@Slf4j
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class TipoAsientoController {
    private final TipoAsientoService tipoAsientoService;

    public TipoAsientoController(TipoAsientoService tipoAsientoService) {
        this.tipoAsientoService = tipoAsientoService;
    }

    @PostMapping(path = "/guardar-tipoAsiento",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarTipoAsiento(@RequestBody TipoAsientoDTO tipoAsientoDTO) {
        try {
            return new ResponseEntity(tipoAsientoService.guardarTipoAsiento(tipoAsientoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtener-tipoAsientos")
    public ResponseEntity<List<TipoAsientoDTO>> obtenerTipoAsientos() {
        return new ResponseEntity(tipoAsientoService.obtenerTipoAsientos(), HttpStatus.OK);
    }

    @GetMapping("/obtener-tipoAsientosActivos")
    public ResponseEntity<List<TipoAsientoDTO>> obtenerTipoAsientosActivos() {
        return new ResponseEntity(tipoAsientoService.obtenerTipoAsientosActivos(), HttpStatus.OK);
    }

    @GetMapping("/obtener-tipoAsiento/{idTipoAsiento}")
    public ResponseEntity<TipoAsientoDTO> obtenerTipoAsiento(@PathVariable("idTipoAsiento") Integer idTipoAsiento) {
        try {
            return new ResponseEntity(tipoAsientoService.obtenerTipoAsientoPorId(idTipoAsiento), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/actualizar-tipoAsiento",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity actualizarTipoAsiento(@RequestBody TipoAsientoDTO tipoAsientoDTO) {
        try {
            return new ResponseEntity(tipoAsientoService.actualizarTipoAsiento(tipoAsientoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/eliminar-tipoAsiento/{idTipoAsiento}")
    public ResponseEntity eliminarTipoAsiento(@PathVariable("idTipoAsiento") Integer idTipoAsiento) {
        try {
            return new ResponseEntity(tipoAsientoService.eliminarTipoAsiento(idTipoAsiento), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
