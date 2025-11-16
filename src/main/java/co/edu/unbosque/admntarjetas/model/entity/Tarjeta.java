package co.edu.unbosque.admntarjetas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Tarjeta {
    @Id
    private Long numeroTarjeta;
    @Column( nullable = false)
    private String fechaVencimiento;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FRANQUICIA franquicia;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ESTADOTARJETA estadoTarjeta;

    private Double cupoTotal;
    private Double cupoDisponible;
    private Double cupoUsado;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

}
