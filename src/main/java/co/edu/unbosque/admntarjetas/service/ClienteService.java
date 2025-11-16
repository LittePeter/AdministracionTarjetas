package co.edu.unbosque.admntarjetas.service;

import co.edu.unbosque.admntarjetas.model.dto.ClienteDto;
import co.edu.unbosque.admntarjetas.model.dto.RegistroDto;

import java.util.List;

public interface ClienteService {
    void createUser (ClienteDto clienteDto);
    void register(RegistroDto registroDto);
    ClienteDto getUser (Long id);
    ClienteDto updateUser (ClienteDto clienteDto);
    void deleteUser (Long id);

    List<ClienteDto> getAllUsers();
}
