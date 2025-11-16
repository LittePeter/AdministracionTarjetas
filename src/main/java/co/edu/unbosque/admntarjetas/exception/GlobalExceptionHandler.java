package co.edu.unbosque.admntarjetas.exception;

import co.edu.unbosque.admntarjetas.model.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TarjetaNotFoundException.class)
    public ResponseEntity<?> tarjetaNoEncontradaException(TarjetaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(Instant.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(TarjetaNoAdmitidaException.class)
    public ResponseEntity<?> tarjetaNoAdmitidaException(TarjetaNoAdmitidaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDto(Instant.now(), HttpStatus.CONFLICT.value(), ex.getMessage()));
    }
}
