package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.dtos.MensajeDTO;
import co.edu.usbcali.airlinesapp.dtos.VueloDTO;
import co.edu.usbcali.airlinesapp.services.interfaces.VueloService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vuelo")
@Slf4j
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class VueloController {
    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @PostMapping(path = "/guardar-vuelo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarVuelo(@RequestBody VueloDTO vueloDTO) {
        try {
            return new ResponseEntity(vueloService.guardarVuelo(vueloDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtener-vuelos")
    public ResponseEntity<List<VueloDTO>> obtenerVuelos(){
        return new ResponseEntity(vueloService.obtenerVuelos(), HttpStatus.OK);
    }

    @GetMapping("/obtener-vuelosActivos")
    public ResponseEntity<List<VueloDTO>> obtenerVuelosActivos(){
        return new ResponseEntity(vueloService.obtenerVuelosActivos(), HttpStatus.OK);
    }

    @GetMapping("/obtener-vuelo/{idVuelo}")
    public ResponseEntity<VueloDTO> obtenerVuelo(@PathVariable("idVuelo") Integer idVuelo){
        try {
            return new ResponseEntity(vueloService.obtenerVueloPorId(idVuelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/actualizar-vuelo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity actualizarVuelo(@RequestBody VueloDTO vueloDTO) {
        try {
            return new ResponseEntity(vueloService.actualizarVuelo(vueloDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/eliminar-vuelo/{idVuelo}")
    public ResponseEntity eliminarVuelo(@PathVariable("idVuelo") Integer idVuelo){
        try {
            return new ResponseEntity(vueloService.eliminarVuelo(idVuelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
