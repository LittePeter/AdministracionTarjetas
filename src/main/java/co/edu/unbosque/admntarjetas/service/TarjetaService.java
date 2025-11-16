package co.edu.unbosque.admntarjetas.service;

import co.edu.unbosque.admntarjetas.model.dto.TarjetaDto;

import java.util.List;

public interface TarjetaService {
    void createTarjeta(TarjetaDto tarjeta);
    TarjetaDto updateTarjeta(TarjetaDto tarjeta);
    void deleteTarjeta(Long numeroTarjeta);
    TarjetaDto getTarjetaDto(TarjetaDto tarjeta);
    List<TarjetaDto> listarTarjetas(Long idCliente);
}
