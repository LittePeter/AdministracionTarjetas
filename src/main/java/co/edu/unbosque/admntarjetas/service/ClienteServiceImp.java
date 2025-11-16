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
        return mapper.map(clienteRepo.findByIdCliente(id), ClienteDto.class);
    }

    @Override
    public ClienteDto updateUser(ClienteDto clienteDto) {
        ClienteDto clientToUpdate = mapper
                .map(clienteRepo.findByIdCliente(clienteDto.getIdCliente()), ClienteDto.class);
        clientToUpdate.setIdCliente(clienteDto.getIdCliente());
        clientToUpdate.setName(clienteDto.getName());
        clientToUpdate.setEmail(clienteDto.getEmail());
        clienteRepo.save(mapper.map(clientToUpdate, Cliente.class));
        return clientToUpdate;
    }

    @Override
    public void deleteUser(Long id) {
        clienteRepo.deleteByIdCliente(id);
    }
}
