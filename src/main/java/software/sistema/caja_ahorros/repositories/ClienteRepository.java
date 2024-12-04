package software.sistema.caja_ahorros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sistema.caja_ahorros.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
