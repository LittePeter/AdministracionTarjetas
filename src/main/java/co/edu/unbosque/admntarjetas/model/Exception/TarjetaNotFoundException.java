package co.edu.unbosque.admntarjetas.model.Exception;

public class TarjetaNotFoundException extends RuntimeException {
    public TarjetaNotFoundException(String message) {
        super(message);
    }
}
