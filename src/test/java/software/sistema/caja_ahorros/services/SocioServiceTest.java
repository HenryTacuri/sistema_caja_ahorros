package software.sistema.caja_ahorros.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import software.sistema.caja_ahorros.Controller.response.SocioResponse;
import software.sistema.caja_ahorros.model.Socio;
import software.sistema.caja_ahorros.repositories.SocioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SocioServiceTest {

    private List<Socio> sociosList = new ArrayList<>();

    @InjectMocks
    SocioServiceImpl socioService;

    @Mock
    SocioRepository socioRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.cargarSocios();
    }

    @Test
    public void consultarTest(){
        when(socioRepository.findAll()).thenReturn(this.sociosList);
        SocioResponse response = socioService.obtenerSocios();

        assertEquals(1,response.getData().size());
        verify(this.socioRepository,times(1)).findAll();
    }


    @Test
    public void getSocioByIdTest() {

        Socio socio = new Socio();
        socio.setId(Long.valueOf("1"));
        socio.setNombre("Nuevo socio");
        socio.setCedula("32423");
        socio.setDireccion("San Francisco");
        socio.setTelefono("432423");

        when(socioRepository.findById("1")).thenReturn(Optional.of(socio));

        SocioResponse response = socioService.buscarSocioPorId(1L);

        Assertions.assertNotNull(response.getData());

        Assertions.assertEquals(1L, response.getData().get(0).getId());
    }

    @Test
    public void eliminarSocio() {
        Socio socio = new Socio();
        when(socioRepository.findById("1")).thenReturn(Optional.of(socio));

        socioService.eliminarSocio(Long.valueOf("1"));

        verify(socioRepository, times(1)).deleteById("1");
    }

    public void cargarSocios(){
        Socio socio1 = new Socio();
        socio1.setId(Long.valueOf("1"));
        socio1.setNombre("Nuevo socio");
        socio1.setCedula("32423");
        socio1.setDireccion("San Francisco");
        socio1.setTelefono("432423");

        sociosList.add(socio1);

    }

}
