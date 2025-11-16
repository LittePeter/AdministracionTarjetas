package co.edu.unbosque.admntarjetas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {
    Instant instant;
    Integer status;
    String message;
}
