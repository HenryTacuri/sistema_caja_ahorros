package software.sistema.caja_ahorros.services;

import software.sistema.caja_ahorros.Controller.response.UsuarioResponse;
import software.sistema.caja_ahorros.model.Usuario;

public interface UsuarioService {

    public UsuarioResponse registrarUsuario(Usuario usuario, Long idSocio);

    public UsuarioResponse actualizarUsuario(Usuario usuario, Long idSocio);

    public UsuarioResponse eliminarUsuario(Long id);

    public UsuarioResponse buscarUsuarioPorId(Long id);

    public UsuarioResponse obtenerUsuarios();

    public UsuarioResponse login(String correo, String contrasenia);

}
