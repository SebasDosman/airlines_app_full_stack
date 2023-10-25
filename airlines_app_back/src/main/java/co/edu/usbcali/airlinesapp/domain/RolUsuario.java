package co.edu.usbcali.airlinesapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rol_usuario")
public class RolUsuario {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rous_id", nullable = false, unique = true)
    private Integer idRolUsuario;

    @Column(name = "descripcion", nullable = false, length = 30)
    private String descripcion;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
}
