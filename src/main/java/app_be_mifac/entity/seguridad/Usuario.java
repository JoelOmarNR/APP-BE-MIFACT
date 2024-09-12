package app_be_mifac.entity.seguridad;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Usuario {
    @Id
    @Column(name = "idusuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombres;
    private String apellidos;
    private String nombreCompleto;
    private String status;
    private String email;
    private String password;
  
    @Enumerated(EnumType.STRING)
    private Rol rol;

    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_act")
    private LocalDateTime fechaActualizacion;

    /**
     * Enumeración que contiene los posibles valores
     * de un Rol para el usuario
     */
    public enum Rol {
        ADMIN,
        CONTADOR,
        RRHH
    }

    @PrePersist
    private void asignarValoresAntesDePersistir() {
        nombreCompleto = nombres + " " + apellidos;
        fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    private void asignarValoresAntesDeActualizar() {
        nombreCompleto = nombres + " " + apellidos;
        fechaActualizacion = LocalDateTime.now();
    }
}
