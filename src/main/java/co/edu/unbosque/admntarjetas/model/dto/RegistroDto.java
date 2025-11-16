package co.edu.unbosque.admntarjetas.model.dto;

import co.edu.unbosque.admntarjetas.model.entity.ESTADOTARJETA;
import co.edu.unbosque.admntarjetas.model.entity.FRANQUICIA;
import lombok.Data;

@Data
public class RegistroDto {

    //Información del Cliente
    private Long idCliente;
    private String name;
    private String email;
    //Información de la trjeta
    private Long numeroTarjeta;
    private String fechaVencimiento;
    private FRANQUICIA franquicia;
    private ESTADOTARJETA estadoTarjeta;
    private Double cupoTotal;
    private Double cupoUsado;
}
