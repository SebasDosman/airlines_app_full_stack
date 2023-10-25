package co.edu.usbcali.airlinesapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trayecto")
public class Trayecto {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tray_id", nullable = false, unique = true)
    private Integer idTrayecto;

    @ManyToOne
    @JoinColumn(name = "avio_id", referencedColumnName = "avio_id")
    private Avion avion;

    @ManyToOne
    @JoinColumn(name = "aero_id_origen", referencedColumnName = "aero_id")
    private Aeropuerto aeropuertoOrigen;

    @ManyToOne
    @JoinColumn(name = "aero_id_destino", referencedColumnName = "aero_id")
    private Aeropuerto aeropuertoDestino;

    @Column(name = "hora_salida", nullable = false)
    private Date horaSalida;

    @Column(name = "hora_llegada", nullable = false)
    private Date horaLlegada;

    @ManyToOne
    @JoinColumn(name = "vuel_id", referencedColumnName = "vuel_id")
    private Vuelo vuelo;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
}
