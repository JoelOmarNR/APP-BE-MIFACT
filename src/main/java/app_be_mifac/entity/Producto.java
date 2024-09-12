package app_be_mifac.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author JOEL OMAR NR
 * @version 1.0
 * @since 11/09/2024
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Producto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter
    @Setter  Long id;

    @Column
    private @Getter @Setter  String nombre;  // Mínimo 10 y máximo 240

    @Column // Mayor o igual que cero
    private @Getter @Setter String descripcion;

    @Column // Mayor o igual que cero
    private @Getter @Setter BigDecimal  cantidad;

    @Column// Mayor que cero
    private @Getter @Setter
    BigDecimal precio;

    @Column
    private @Getter @Setter
    LocalDate fechaCreacion;
    @Column
    private @Getter @Setter LocalDate fechaActualizacion;

    @PrePersist
    private void asignarValoresAntesDePersistir() {
        fechaCreacion = LocalDate.now();
    }


    @PreUpdate
    private void asignarValoresAntesDeActualizar() {
        fechaActualizacion = LocalDate.now();
    }



}
