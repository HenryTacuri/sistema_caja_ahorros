package software.sistema.caja_ahorros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sistema.caja_ahorros.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
