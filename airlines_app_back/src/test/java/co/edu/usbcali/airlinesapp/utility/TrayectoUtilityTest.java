package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Trayecto;
import co.edu.usbcali.airlinesapp.dtos.TrayectoDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TrayectoUtilityTest {
    public static Integer ID_UNO = 1;
    public static Date HORA_SALIDA_UNO = new Date();
    public static Date HORA_LLEGADA_UNO = new Date();
    public static String ESTADO_UNO = "A";
    public static Integer TRAYECTOS_SIZE = 2;
    public static Integer TRAYECTOS_VACIO_SIZE = 0;

    public static Trayecto TRAYECTO_UNO = Trayecto.builder()
            .idTrayecto(1)
            .avion(AvionUtilityTest.AVION_UNO)
            .aeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO)
            .aeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS)
            .horaSalida(new Date())
            .horaLlegada(new Date())
            .vuelo(VueloUtilityTest.VUELO_UNO)
            .estado("A")
            .build();

    public static Trayecto TRAYECTO_DOS = Trayecto.builder()
            .idTrayecto(2)
            .avion(AvionUtilityTest.AVION_UNO)
            .aeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO)
            .aeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS)
            .horaSalida(new Date())
            .horaLlegada(new Date())
            .vuelo(VueloUtilityTest.VUELO_UNO)
            .estado("A")
            .build();

    public static TrayectoDTO TRAYECTODTO = TrayectoDTO.builder()
            .idTrayecto(1)
            .idAvion(AvionUtilityTest.AVION_UNO.getIdAvion())
            .idAeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())
            .idAeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS.getIdAeropuerto())
            .horaSalida(new Date())
            .horaLlegada(new Date())
            .idVuelo(VueloUtilityTest.VUELO_UNO.getIdVuelo())
            .estado("A")
            .build();

    public static List<Trayecto> TRAYECTOS = Arrays.asList(TRAYECTO_UNO, TRAYECTO_DOS);

    public static List<Trayecto> TRAYECTOS_VACIO = Arrays.asList();
}
