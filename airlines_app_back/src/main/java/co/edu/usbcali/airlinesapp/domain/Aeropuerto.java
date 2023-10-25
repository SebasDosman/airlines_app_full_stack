package co.edu.usbcali.airlinesapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "aeropuerto")
public class Aeropuerto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aero_id", nullable = false, unique = true)
    private Integer idAeropuerto;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @Column(name = "iata", nullable = false, length = 5)
    private String iata;

    @Column(name = "ubicacion", nullable = false, length = 80)
    private String ubicacion;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
}
