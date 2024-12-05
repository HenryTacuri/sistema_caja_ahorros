package software.sistema.caja_ahorros.repositories;

import software.sistema.caja_ahorros.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, String>{

}
