package software.sistema.caja_ahorros.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.sistema.caja_ahorros.Controller.response.CuentaResponse;
import software.sistema.caja_ahorros.model.Cuenta;
import software.sistema.caja_ahorros.model.Socio;
import software.sistema.caja_ahorros.model.Usuario;
import software.sistema.caja_ahorros.repositories.CuentaRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CuentaServiceTest {

    private List<Cuenta> cuentasList = new ArrayList<>();

    @InjectMocks
    CuentaServiceImpl cuentaService;

    @Mock
    CuentaRepository cuentaRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.cargarCuentas();
    }

    @Test
    public void consultarTest(){
        when(cuentaRepository.findAll()).thenReturn(this.cuentasList);
        CuentaResponse response = cuentaService.obtenerCuentas();

        assertEquals(1,response.getData().size());
        verify(this.cuentaRepository,times(1)).findAll();
    }

    @Test
    public void getCuentaByIdTest() {

        Socio socio1 = new Socio();
        socio1.setId(Long.valueOf("1"));
        socio1.setNombre("Nuevo socio");
        socio1.setCedula("32423");
        socio1.setDireccion("San Francisco");
        socio1.setTelefono("432423");


        Usuario usuario1 = new Usuario();
        usuario1.setId(Long.valueOf("1"));
        usuario1.setCorreo("usuario1@gmail.com");
        usuario1.setContrasenia("1234");
        usuario1.setTipo("Admin");
        usuario1.setSocio(socio1);

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setId(Long.valueOf("1"));
        cuenta1.setNumeroCuenta(7415);
        cuenta1.setSaldo(new BigDecimal("150.75"));
        cuenta1.setSocio(socio1);

        when(cuentaRepository.findById("1")).thenReturn(Optional.of(cuenta1));

        CuentaResponse response = cuentaService.buscarCuentaPorId(1L);

        Assertions.assertNotNull(response.getData());

        Assertions.assertEquals(1L, response.getData().get(0).getId());
    }

    @Test
    public void eliminarCuentaTest() {
        Cuenta cuenta = new Cuenta();
        when(cuentaRepository.findById("1")).thenReturn(Optional.of(cuenta));

        cuentaService.eliminarCuenta(Long.valueOf("1"));

        verify(cuentaRepository, times(1)).deleteById("1");
    }


    public void cargarCuentas(){

        Socio socio1 = new Socio();
        socio1.setId(Long.valueOf("1"));
        socio1.setNombre("Nuevo socio");
        socio1.setCedula("32423");
        socio1.setDireccion("San Francisco");
        socio1.setTelefono("432423");


        Usuario usuario1 = new Usuario();
        usuario1.setId(Long.valueOf("1"));
        usuario1.setCorreo("usuario1@gmail.com");
        usuario1.setContrasenia("1234");
        usuario1.setTipo("Admin");
        usuario1.setSocio(socio1);

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setId(Long.valueOf("1"));
        cuenta1.setNumeroCuenta(7415);
        cuenta1.setSaldo(new BigDecimal("150.75"));
        cuenta1.setSocio(socio1);


        cuentasList.add(cuenta1);
    }

}
