package co.edu.unbosque.admntarjetas.model.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Cliente {
    @Id
    @Column(length=10,nullable=false,unique=true)
    private Long idCliente;
    @Column
    private String name;
    @Column (unique = true)
    private String email;
}
