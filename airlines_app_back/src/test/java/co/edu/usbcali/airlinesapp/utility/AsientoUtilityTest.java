package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Asiento;
import co.edu.usbcali.airlinesapp.dtos.AsientoDTO;

import java.util.Arrays;
import java.util.List;

public class AsientoUtilityTest {
    public static Integer ID_UNO = 1;
    public static String UBICACION_UNO = "A1";
    public static String ESTADO_UNO = "A";
    public static Integer ASIENTOS_SIZE = 2;
    public static Integer ASIENTOS_VACIO_SIZE = 0;

    public static Asiento ASIENTO_UNO = Asiento.builder()
            .idAsiento(1)
            .tipoAsiento(TipoAsientoUtilityTest.TIPOASIENTO_UNO)
            .avion(AvionUtilityTest.AVION_UNO)
            .ubicacion("A1")
            .estado("A")
            .build();

    public static Asiento ASIENTO_DOS = Asiento.builder()
            .idAsiento(2)
            .tipoAsiento(TipoAsientoUtilityTest.TIPOASIENTO_UNO)
            .avion(AvionUtilityTest.AVION_UNO)
            .ubicacion("A2")
            .estado("A")
            .build();

    public static AsientoDTO ASIENTODTO = AsientoDTO.builder()
            .idAsiento(1)
            .idTipoAsiento(ASIENTO_UNO.getTipoAsiento().getIdTipoAsiento())
            .idAvion(ASIENTO_DOS.getAvion().getIdAvion())
            .ubicacion("A1")
            .estado("A")
            .build();

    public static List<Asiento> ASIENTOS = Arrays.asList(ASIENTO_UNO, ASIENTO_DOS);

    public static List<Asiento> ASIENTOS_VACIO = Arrays.asList();
}
