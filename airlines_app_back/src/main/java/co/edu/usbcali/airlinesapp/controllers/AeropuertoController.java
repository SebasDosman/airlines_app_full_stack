package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;
import co.edu.usbcali.airlinesapp.dtos.MensajeDTO;
import co.edu.usbcali.airlinesapp.services.interfaces.AeropuertoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aeropuerto")
@Slf4j
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class AeropuertoController {
    private final AeropuertoService aeropuertoService;

    public AeropuertoController(AeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }

    @PostMapping(path = "/guardar-aeropuerto",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarAeropuerto(@RequestBody AeropuertoDTO aeropuertoDTO) {
        try {
            System.out.println( aeropuertoDTO );
            return new ResponseEntity(aeropuertoService.guardarAeropuerto(aeropuertoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtener-aeropuertos")
    public ResponseEntity<List<AeropuertoDTO>> obtenerAeropuertos() {
        return new ResponseEntity(aeropuertoService.obtenerAeropuertos(), HttpStatus.OK);
    }

    @GetMapping("/obtener-aeropuertosActivos")
    public ResponseEntity<List<AeropuertoDTO>> obtenerAeropuertosActivos() {
        return new ResponseEntity(aeropuertoService.obtenerAeropuertosActivos(), HttpStatus.OK);
    }

    @GetMapping(value = "/obtener-aeropuerto/{idAeropuerto}")
    public ResponseEntity<AeropuertoDTO> obtenerAeropuerto(@PathVariable("idAeropuerto") Integer idAeropuerto) {
        try {
            return new ResponseEntity(aeropuertoService.obtenerAeropuertoPorId(idAeropuerto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/actualizar-aeropuerto",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity actualizarAeropuerto(@RequestBody AeropuertoDTO aeropuertoDTO) {
        try {
            return new ResponseEntity(aeropuertoService.actualizarAeropuerto(aeropuertoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/eliminar-aeropuerto/{idAeropuerto}")
    public ResponseEntity eliminarAeropuerto(@PathVariable("idAeropuerto") Integer idAeropuerto) {
        try {
            return new ResponseEntity(aeropuertoService.eliminarAeropuerto(idAeropuerto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
