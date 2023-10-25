package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Vuelo;
import co.edu.usbcali.airlinesapp.dtos.VueloDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class VueloUtilityTest {
    public static Integer ID_UNO = 1;
    public static long PRECIO_UNO = 100000;
    public static Date HORA_SALIDA_UNO = new Date();
    public static Date HORA_LLEGADA_UNO = new Date();
    public static long PRECIO_ASIENTO_VIP_UNO = 50000;
    public static long PRECIO_ASIENTO_NORMAL_UNO = 30000;
    public static long PRECIO_ASIENTO_BASICO_UNO = 10000;
    public static String ESTADO_UNO = "A";
    public static Integer VUELOS_SIZE = 2;
    public static Integer VUELOS_VACIO_SIZE = 0;

    public static Vuelo VUELO_UNO = Vuelo.builder()
            .idVuelo(1)
            .aeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO)
            .aeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS)
            .precio(100000)
            .horaSalida(new Date())
            .horaLlegada(new Date())
            .precioAsientoVip(50000)
            .precioAsientoNormal(30000)
            .precioAsientoBasico(10000)
            .estado("A")
            .build();

    public static Vuelo VUELO_DOS = Vuelo.builder()
            .idVuelo(2)
            .aeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO)
            .aeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS)
            .precio(150000)
            .horaSalida(new Date())
            .horaLlegada(new Date())
            .precioAsientoVip(80000)
            .precioAsientoNormal(50000)
            .precioAsientoBasico(30000)
            .estado("A")
            .build();

    public static VueloDTO VUELODTO = VueloDTO.builder()
            .idVuelo(1)
            .idAeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())
            .idAeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS.getIdAeropuerto())
            .precio(100000)
            .horaSalida(new Date())
            .horaLlegada(new Date())
            .precioAsientoVip(50000)
            .precioAsientoNormal(30000)
            .precioAsientoBasico(10000)
            .estado("A")
            .build();

    public static List<Vuelo> VUELOS = Arrays.asList(VUELO_UNO, VUELO_DOS);

    public static List<Vuelo> VUELOS_VACIO = Arrays.asList();
}
