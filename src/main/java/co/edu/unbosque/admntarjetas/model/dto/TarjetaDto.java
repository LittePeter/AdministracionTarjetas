package co.edu.unbosque.admntarjetas.model.dto;

import co.edu.unbosque.admntarjetas.model.entity.ESTADOTARJETA;
import co.edu.unbosque.admntarjetas.model.entity.FRANQUICIA;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaDto {
    private Long numeroTarjeta;
    private String fechaVencimiento;
    private FRANQUICIA franquicia;
    private ESTADOTARJETA estadoTarjeta;
    private Double cupoTotal;
    private Double cupoDisponible;
    private Double cupoUsado;
    private Long idCliente;
}
