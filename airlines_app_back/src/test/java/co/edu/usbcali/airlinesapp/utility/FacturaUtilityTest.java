package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Factura;
import co.edu.usbcali.airlinesapp.dtos.FacturaDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FacturaUtilityTest {
    public static Integer ID_UNO = 1;
    public static Date FECHA_UNO = new Date();
    public static String ESTADO_UNO = "A";
    public static Integer FACTURAS_SIZE = 2;
    public static Integer FACTURAS_VACIO_SIZE = 0;

    public static Factura FACTURA_UNO = Factura.builder()
            .idFactura(1)
            .reserva(ReservaUtilityTest.RESERVA_UNO)
            .fecha(new Date())
            .estado("A")
            .build();

    public static Factura FACTURA_DOS = Factura.builder()
            .idFactura(2)
            .reserva(ReservaUtilityTest.RESERVA_UNO)
            .fecha(new Date())
            .estado("A")
            .build();

    public static FacturaDTO FACTURADTO = FacturaDTO.builder()
            .idFactura(1)
            .idReserva(ReservaUtilityTest.RESERVA_UNO.getIdReserva())
            .fecha(new Date())
            .estado("A")
            .build();

    public static List<Factura> FACTURAS = Arrays.asList(FACTURA_UNO, FACTURA_DOS);

    public static List<Factura> FACTURAS_VACIO = Arrays.asList();
}
