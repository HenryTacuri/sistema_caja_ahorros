package software.sistema.caja_ahorros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sistema.caja_ahorros.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByCorreoAndContrasenia(String correo, String contrasenia);

}
