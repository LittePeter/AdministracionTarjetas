package co.edu.unbosque.admntarjetas.service;

import co.edu.unbosque.admntarjetas.model.dto.ClienteDto;
import co.edu.unbosque.admntarjetas.model.entity.Cliente;
import co.edu.unbosque.admntarjetas.repo.ClienteRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImpTest {

    @Mock
    private ClienteRepo clienteRepo;

    @InjectMocks
    private ClienteService service = new ClienteServiceImp();

    @Mock
    private ModelMapper mapper;

    @Test
    void createUser() {
        ClienteDto dto = new ClienteDto();
        dto.setIdCliente(1L);
        dto.setName("Juan");
        dto.setEmail("juan@test.com");

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setName("Juan");
        cliente.setEmail("juan@test.com");

        when(mapper.map(dto, Cliente.class)).thenReturn(cliente);

        service.createUser(dto);

        verify(clienteRepo, times(1)).save(cliente);
    }

    @Test
    void getUser() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setName("Ana");
        cliente.setEmail("ana@test.com");

        ClienteDto dto = new ClienteDto();
        dto.setIdCliente(1L);
        dto.setName("Ana");
        dto.setEmail("ana@test.com");

        when(clienteRepo.findByIdCliente(1L)).thenReturn(cliente);
        when(mapper.map(cliente, ClienteDto.class)).thenReturn(dto);

        ClienteDto result = service.getUser(1L);

        assertNotNull(result);
        assertEquals("Ana", result.getName());
        assertEquals("ana@test.com", result.getEmail());
    }

    @Test
    void updateUser() {
        ClienteDto clienteAEditar = new ClienteDto();
        clienteAEditar.setIdCliente(1L);
        clienteAEditar.setName("Nuevo");
        clienteAEditar.setEmail("nuevo@test.com");

        Cliente clienteExistente = new Cliente();
        clienteExistente.setIdCliente(1L);
        clienteExistente.setName("Viejo");
        clienteExistente.setEmail("viejo@test.com");

        ClienteDto dtoExistenteMappeado = new ClienteDto();
        dtoExistenteMappeado.setIdCliente(1L);
        dtoExistenteMappeado.setName("Viejo");
        dtoExistenteMappeado.setEmail("viejo@test.com");

        Cliente clienteAGuardar = new Cliente();
        clienteAGuardar.setIdCliente(1L);
        clienteAGuardar.setName("Nuevo");
        clienteAGuardar.setEmail("nuevo@test.com");


        when(clienteRepo.findByIdCliente(1L)).thenReturn(clienteExistente);
        when(mapper.map(clienteExistente, ClienteDto.class)).thenReturn(dtoExistenteMappeado);
        when(mapper.map(dtoExistenteMappeado, Cliente.class)).thenReturn(clienteAGuardar);

        ClienteDto returned = service.updateUser(clienteAEditar);

        assertEquals("Nuevo", returned.getName());
        assertEquals("nuevo@test.com", returned.getEmail());

        verify(clienteRepo, times(1)).save(clienteAGuardar);
    }

    @Test
    void deleteUser() {
        service.deleteUser(5L);
        verify(clienteRepo, times(1)).deleteByIdCliente(5L);
    }

    @Test
    void getAllUsers() {
        Cliente c1 = new Cliente();
        c1.setIdCliente(1L);
        c1.setName("A");
        c1.setEmail("a@test.com");

        Cliente c2 = new Cliente();
        c2.setIdCliente(2L);
        c2.setName("B");
        c2.setEmail("b@test.com");

        ClienteDto dto1 = new ClienteDto();
        dto1.setIdCliente(1L);
        dto1.setName("A");
        dto1.setEmail("a@test.com");

        ClienteDto dto2 = new ClienteDto();
        dto2.setIdCliente(2L);
        dto2.setName("B");
        dto2.setEmail("b@test.com");

        when(clienteRepo.findAll()).thenReturn(Arrays.asList(c1, c2));
        when(mapper.map(c1, ClienteDto.class)).thenReturn(dto1);
        when(mapper.map(c2, ClienteDto.class)).thenReturn(dto2);

        List<ClienteDto> result = service.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getName());
        assertEquals("B", result.get(1).getName());
    }
}