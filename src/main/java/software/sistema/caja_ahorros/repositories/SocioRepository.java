package software.sistema.caja_ahorros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sistema.caja_ahorros.model.Socio;

public interface SocioRepository extends JpaRepository<Socio, String> {

}
