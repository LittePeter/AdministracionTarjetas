package co.edu.unbosque.admntarjetas.controller;

import co.edu.unbosque.admntarjetas.model.dto.ClienteDto;
import co.edu.unbosque.admntarjetas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class CienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> createUser(@RequestBody ClienteDto clienteDto) {
        clienteService.createUser(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
    }

    @PutMapping
    public ResponseEntity<ClienteDto> updateUser(@RequestBody ClienteDto clienteDto) {
        clienteService.updateUser(clienteDto);
        return ResponseEntity.ok().body(clienteDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.getUser(id));
    }
    @DeleteMapping
    public ResponseEntity<ClienteDto> deleteUser(@RequestParam Long clienteId) {
        clienteService.deleteUser(clienteId);
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClienteDto>> getAllUsers() {
        return ResponseEntity.ok().body(clienteService.getAllUsers());
    }
}
