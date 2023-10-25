package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;
import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;

import java.util.Arrays;
import java.util.List;

public class AeropuertoUtilityTest {
    public static Integer ID_UNO = 1;
    public static String NOMBRE_UNO = "Aeropuerto Internacional El Dorado";
    public static String IATA_UNO = "BOG";
    public static String UBICACION_UNO = "Bogotá";
    public static String ESTADO_UNO = "A";
    public static Integer ID_DOS = 2;
    public static String NOMBRE_DOS = "Aeropuerto Internacional Alfonso Bonilla Aragón";
    public static String IATA_DOS = "COL";
    public static String UBICACION_DOS = "Santiago de Cali";
    public static String ESTADO_DOS = "A";
    public static Integer AEROPUERTOS_SIZE = 2;
    public static Integer AEROPUERTOS_VACIO_SIZE = 0;

    public static Aeropuerto AEROPUERTO_UNO = Aeropuerto.builder()
            .idAeropuerto(1)
            .nombre("Aeropuerto Internacional El Dorado")
            .iata("BOG")
            .ubicacion("Bogotá")
            .estado("A")
            .build();

    public static Aeropuerto AEROPUERTO_DOS = Aeropuerto.builder()
            .idAeropuerto(2)
            .nombre("Aeropuerto Internacional Alfonso Bonilla Aragón")
            .iata("COL")
            .ubicacion("Santiago de Cali")
            .estado("A")
            .build();

    public static AeropuertoDTO AEROPUERTODTO = AeropuertoDTO.builder()
            .idAeropuerto(1)
            .nombre("Aeropuerto Internacional El Dorado")
            .iata("BOG")
            .ubicacion("Bogotá")
            .estado("A")
            .build();

    public static List<Aeropuerto> AEROPUERTOS = Arrays.asList(AEROPUERTO_UNO, AEROPUERTO_DOS);

    public static List<Aeropuerto> AEROPUERTOS_VACIO = Arrays.asList();
}
