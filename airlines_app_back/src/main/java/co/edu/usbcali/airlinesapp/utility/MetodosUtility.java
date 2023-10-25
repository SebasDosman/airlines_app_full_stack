package co.edu.usbcali.airlinesapp.utility;

import java.util.Date;

public class MetodosUtility {
    public static boolean esFechaActualOReciente(Date fecha) {
        Date fechaActual = new Date();

        return fecha.after(fechaActual);
    }
}
