package co.edu.unbosque.admntarjetas.service;

import co.edu.unbosque.admntarjetas.model.dto.ClienteDto;
import co.edu.unbosque.admntarjetas.model.entity.Cliente;
import co.edu.unbosque.admntarjetas.repo.ClienteRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    ClienteRepo clienteRepo;

    @Override
    public void createUser(ClienteDto clienteDto) {
        clienteRepo.save(mapper.map(clienteDto, Cliente.class));
    }

    @Override
    public ClienteDto getUser(Long id) {
        return null;
    }

    @Override
    public ClienteDto updateUser(ClienteDto clienteDto) {
        return null;
    }

    @Override
    public ClienteDto deleteUser(Long id) {
        return null;
    }
}
