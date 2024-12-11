import org.junit.jupiter.api.Assertions;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CreditoServicesImplTest {
    @InjectMocks
    private CreditoServicesImpl creditoService;

    @Mock
    private CreditoRepository creditoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    private List<Credito> CreditoList= new ArrayList<>();
    private List<Cuenta> CuentaList= new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        cargarDatos();
    }

    @Test
    public void registrarCreditoTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(Long.valueOf("1"));
        cuenta.setNumeroCuenta(12875);
        cuenta.setSaldo(BigDecimal.valueOf(100));

        Credito credito = new Credito();
        credito.setId(Long.valueOf("1"));
        credito.setValor(450.34); // Valor del crédito a agregar
        LocalDate fechaA = LocalDate.of(2024, 10, 12);
        LocalDate fechaL = LocalDate.of(2024, 10, 12);
        Date fechaAcreditada = Date.from(fechaA.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaLiquidacion = Date.from(fechaL.atStartOfDay(ZoneId.systemDefault()).toInstant());
        credito.setFechaAcreditada(fechaAcreditada);
        credito.setFechaLiquidacion(fechaLiquidacion);

        when(cuentaRepository.findById("1")).thenReturn(Optional.of(cuenta));
        when(cuentaRepository.save(cuenta)).thenReturn(cuenta);
        when(creditoRepository.save(credito)).thenReturn(credito);
        CreditoResponse response = creditoService.registrarCredito(credito, 1L);
        assertEquals(BigDecimal.valueOf(550.34), cuenta.getSaldo());

        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());
        assertEquals(1L, response.getData().get(0).getId());
        assertEquals(cuenta, credito.getCuenta());

        verify(cuentaRepository).findById("1");
        verify(cuentaRepository).save(cuenta);
        verify(creditoRepository).save(credito);
    }

    @Test
    public void actualizarCreditoTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(Long.valueOf("1"));
        cuenta.setNumeroCuenta(12875);
        cuenta.setSaldo(BigDecimal.valueOf(100));

        Credito credito = new Credito();
        credito.setId(Long.valueOf("1"));
        credito.setValor(450.34);
        LocalDate fechaA = LocalDate.of(2024, 10, 12);
        LocalDate fechaL = LocalDate.of(2024, 10, 12);
        Date fechaAcreditada = Date.from(fechaA.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaLiquidacion = Date.from(fechaL.atStartOfDay(ZoneId.systemDefault()).toInstant());
        credito.setFechaAcreditada(fechaAcreditada);
        credito.setFechaLiquidacion(fechaLiquidacion);

        when(cuentaRepository.findById("1")).thenReturn(Optional.of(cuenta));
        when(creditoRepository.save(credito)).thenReturn(credito);

        CreditoResponse response = creditoService.actualizarCredito(credito, 1L);

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());
        assertEquals(credito, response.getData().get(0));

        verify(creditoRepository, times(1)).save(credito);
    }

    @Test
    public void getCreditoByIdTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(Long.valueOf("1"));
        cuenta.setNumeroCuenta(12875);
        cuenta.setSaldo(BigDecimal.valueOf(100));

        when(cuentaRepository.findById("1")).thenReturn(Optional.of(cuenta));
        Credito credito = new Credito();
        credito.setId(1L);
        when(creditoRepository.findById("1")).thenReturn(Optional.of(credito));

        CreditoResponse response = creditoService.buscarCreditoPorId(1L);
        assertNotNull(response.getData());
        Assertions.assertEquals(1L, response.getData().get(0).getId());
    }


    @Test
    public void obtenerCreditosTest(){
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
