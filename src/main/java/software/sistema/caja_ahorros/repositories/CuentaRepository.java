package software.sistema.caja_ahorros.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import software.sistema.caja_ahorros.model.Cuenta;
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
}