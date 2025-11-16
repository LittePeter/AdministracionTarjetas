package co.edu.unbosque.admntarjetas.repo;

import co.edu.unbosque.admntarjetas.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente,Long> {
}
