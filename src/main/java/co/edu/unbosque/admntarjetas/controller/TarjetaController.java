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

    @PutMapping("/update/{numeroTarjeta}")
    ResponseEntity <TarjetaDto> updateTarjeta(@PathVariable long numeroTarjeta, @RequestBody TarjetaDto tarjetaDto) {
        tarjetaDto.setNumeroTarjeta(numeroTarjeta);
        return ResponseEntity.ok().body(tarjetaService.updateTarjeta(tarjetaDto));
    }

    @GetMapping("/{idCliente}")
    ResponseEntity <List<TarjetaDto>> getTarjetas(@PathVariable long idCliente) {
        return ResponseEntity.ok().body(tarjetaService.listarTarjetas(idCliente));
    }
    @DeleteMapping("/{idTarjeta}")
    public ResponseEntity<Void> deleteTarjeta(@PathVariable long idTarjeta) {
        tarjetaService.deleteTarjeta(idTarjeta);
        return ResponseEntity.noContent().build();
    }
}
