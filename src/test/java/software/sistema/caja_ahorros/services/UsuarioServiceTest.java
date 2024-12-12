package software.sistema.caja_ahorros.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.sistema.caja_ahorros.Controller.response.UsuarioResponse;
import software.sistema.caja_ahorros.model.Socio;
import software.sistema.caja_ahorros.model.Usuario;
import software.sistema.caja_ahorros.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    private List<Usuario> usuariosList = new ArrayList<>();

    @InjectMocks
    UsuarioServiceImpl usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.cargarUsuarios();
    }

    @Test
    public void consultarTest(){
        when(usuarioRepository.findAll()).thenReturn(this.usuariosList);
        UsuarioResponse response = usuarioService.obtenerUsuarios();

        assertEquals(2,response.getData().size());
        verify(this.usuarioRepository,times(1)).findAll();
    }

    @Test
    public void getUsuarioByIdTest() {

        Socio socio = new Socio();
        socio.setId(Long.valueOf("3"));
        socio.setNombre("Ana Gómez");
        socio.setCedula("67890");
        socio.setDireccion("Avenida Remigio");
        socio.setTelefono("555888999");

        Usuario usuario = new Usuario();
        usuario.setId(Long.valueOf("3"));
        usuario.setCorreo("anagomez@gmail.com");
        usuario.setContrasenia("abc123");
        usuario.setTipo("Admin");
        usuario.setSocio(socio);

        when(usuarioRepository.findById("3")).thenReturn(Optional.of(usuario));

        UsuarioResponse response = usuarioService.buscarUsuarioPorId(3L);

        Assertions.assertNotNull(response.getData());

        Assertions.assertEquals(3L, response.getData().get(0).getId());
    }

    @Test
    public void eliminarUsuarioTest() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));

        usuarioService.eliminarUsuario(Long.valueOf("1"));

        verify(usuarioRepository, times(1)).deleteById("1");
    }

    public void cargarUsuarios(){

        Socio socio1 = new Socio();
        socio1.setId(Long.valueOf("1"));
        socio1.setNombre("Nuevo socio");
        socio1.setCedula("32423");
        socio1.setDireccion("San Francisco");
        socio1.setTelefono("432423");

        Socio socio2 = new Socio();
        socio2.setId(Long.valueOf("2"));
        socio2.setNombre("Juan Pérez");
        socio2.setCedula("12345");
        socio2.setDireccion("Avenida Loja");
        socio2.setTelefono("987654321");

        Usuario usuario1 = new Usuario();
        usuario1.setId(Long.valueOf("1"));
        usuario1.setCorreo("usuario1@gmail.com");
        usuario1.setContrasenia("1234");
        usuario1.setTipo("Admin");
        usuario1.setSocio(socio1);

        Usuario usuario2 = new Usuario();
        usuario2.setId(Long.valueOf("2"));
        usuario2.setCorreo("juanperez@gmail.com");
        usuario2.setContrasenia("password123");
        usuario2.setTipo("General");
        usuario2.setSocio(socio2);

        usuariosList.add(usuario1);
        usuariosList.add(usuario2);
    }

}
