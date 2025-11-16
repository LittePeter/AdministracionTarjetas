package co.edu.unbosque.admntarjetas.service;

import co.edu.unbosque.admntarjetas.model.dto.ClienteDto;

import java.util.List;

public interface ClienteService {
    void createUser (ClienteDto clienteDto);
    ClienteDto getUser (Long id);
    ClienteDto updateUser (ClienteDto clienteDto);
    void deleteUser (Long id);

    List<ClienteDto> getAllUsers();
}
