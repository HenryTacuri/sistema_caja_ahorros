import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.sistema.caja_ahorros.Controller.response.CreditoResponse;
import software.sistema.caja_ahorros.model.Credito;
import software.sistema.caja_ahorros.model.Cuenta;
import software.sistema.caja_ahorros.repositories.CreditoRepository;
import software.sistema.caja_ahorros.repositories.CuentaRepository;
import software.sistema.caja_ahorros.services.CreditoServicesImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CreditoServicesImplTest {
    @InjectMocks
    private CreditoServicesImpl creditoService;

    @Mock
    private CreditoRepository creditoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    private Cuenta mockCuenta;
    private Credito mockCredito;
    private List<Credito> CreditoList= new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        cargarDatos();
    }


    @Test
    public void consultarCreditos(){
        when(creditoRepository.findAll()).thenReturn(this.CreditoList);
        CreditoResponse response = creditoService.obtenerCreditos();

        assertEquals(1,response.getData().size());
        verify(this.creditoRepository,times(1)).findAll();
    }

    



    private void cargarDatos() {
        Credito credito1 = new Credito();
        credito1.setId(Long.valueOf("1"));
        credito1.setValor(450.34);
        LocalDate fechaA = LocalDate.of(2024, 10, 12);
        LocalDate fechaL = LocalDate.of(2024, 10, 12);
        Date fechaAcreditada = Date.from(fechaA.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaLiquidacion = Date.from(fechaL.atStartOfDay(ZoneId.systemDefault()).toInstant());
        credito1.setFechaAcreditada(fechaAcreditada);
        credito1.setFechaLiquidacion(fechaLiquidacion);

        CreditoList.add(credito1);
    }
}
