package co.edu.unbosque.admntarjetas.controller;

import co.edu.unbosque.admntarjetas.model.dto.ClienteDto;
import co.edu.unbosque.admntarjetas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class CienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> createUser(@RequestBody ClienteDto clienteDto) {
        clienteService.createUser(clienteDto);
        return ResponseEntity.ok().body(clienteDto);
    }

    @PutMapping
    public ResponseEntity<ClienteDto> updateUser(@RequestBody ClienteDto clienteDto) {
        clienteService.updateUser(clienteDto);
        return ResponseEntity.ok().body(clienteDto);
    }

    @DeleteMapping
    public ResponseEntity<ClienteDto> deleteUser(@RequestParam Long clienteId) {
        clienteService.deleteUser(clienteId);
        return null;
    }
}
