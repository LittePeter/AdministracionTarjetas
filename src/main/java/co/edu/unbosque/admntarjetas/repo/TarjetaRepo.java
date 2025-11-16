package co.edu.unbosque.admntarjetas.repo;

import co.edu.unbosque.admntarjetas.model.entity.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TarjetaRepo extends JpaRepository<Tarjeta,Long> {
    List<Tarjeta> findByCliente_IdCliente(Long cliente);
    boolean existsByNumeroTarjeta(Long numeroTarjeta);

    Optional<Tarjeta> findByNumeroTarjeta(Long numeroTarjeta);
}
