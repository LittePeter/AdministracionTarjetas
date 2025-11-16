package co.edu.unbosque.admntarjetas.controller;
import co.edu.unbosque.admntarjetas.model.dto.TarjetaDto;
import co.edu.unbosque.admntarjetas.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class TarjetaController {
    @Autowired
    TarjetaService tarjetaService;

    @PostMapping
    public ResponseEntity<TarjetaDto> createTarjeta(@RequestBody TarjetaDto tarjetaDto) {
        tarjetaService.createTarjeta(tarjetaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaDto);
    }

    @PutMapping
    ResponseEntity <TarjetaDto> updateTarjeta(@RequestBody TarjetaDto tarjetaDto) {
        return ResponseEntity.ok().body(tarjetaService.updateTarjeta(tarjetaDto));
    }

    @GetMapping("/{idCliente}")
    ResponseEntity <List<TarjetaDto>> updateTarjeta(@PathVariable long idCliente) {
        return ResponseEntity.ok().body(tarjetaService.listarTarjetas(idCliente));
    }
}
