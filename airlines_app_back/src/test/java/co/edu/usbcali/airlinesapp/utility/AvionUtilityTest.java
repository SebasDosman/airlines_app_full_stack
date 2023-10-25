package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Avion;
import co.edu.usbcali.airlinesapp.dtos.AvionDTO;

import java.util.Arrays;
import java.util.List;

public class AvionUtilityTest {
    public static Integer ID_UNO = 1;
    public static String MODELO_UNO = "Boeing 737";
    public static String AEROLINEA_UNO = "Avianca";
    public static String ESTADO_UNO = "A";
    public static Integer AVIONES_SIZE = 2;
    public static Integer AVIONES_VACIO_SIZE = 0;

    public static Avion AVION_UNO = Avion.builder()
            .idAvion(1)
            .modelo("Boeing 737")
            .aerolinea("Avianca")
            .estado("A")
            .build();

    public static Avion AVION_DOS = Avion.builder()
            .idAvion(2)
            .modelo("Boeing 747")
            .aerolinea("Wingo")
            .estado("A")
            .build();

    public static AvionDTO AVIONDTO = AvionDTO.builder()
            .idAvion(1)
            .modelo("Boeing 737")
            .aerolinea("Avianca")
            .estado("A")
            .build();

    public static List<Avion> AVIONES = Arrays.asList(AVION_UNO, AVION_DOS);

    public static List<Avion> AVIONES_VACIO = Arrays.asList();
}
