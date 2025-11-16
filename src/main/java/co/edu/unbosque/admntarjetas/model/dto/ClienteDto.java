package co.edu.unbosque.admntarjetas.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Long idCliente;
    private String name;
    private String email;
}
