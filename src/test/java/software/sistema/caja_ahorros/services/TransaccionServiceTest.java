package software.sistema.caja_ahorros.services;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import software.sistema.caja_ahorros.Controller.TransaccionController;
import software.sistema.caja_ahorros.Controller.response.TransaccionResponse;
import software.sistema.caja_ahorros.model.Transaccion;
import software.sistema.caja_ahorros.repositories.TransaccionRepository;
import software.sistema.caja_ahorros.repositories.CuentaRepository;
import software.sistema.caja_ahorros.model.Cuenta;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


public class TransaccionServiceTest {

    private List<Transaccion> transaccionList;

    @InjectMocks
    TransaccionServiceImpl transaccionService;

    @Mock
    TransaccionRepository transaccionRepository;

    @Mock
    CuentaRepository cuentaRepository;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transaccionList= new ArrayList<>();
        this.cargarTransacciones();
    }

    @Test
    public void obtenerTransaccionesTest() {
        when(transaccionRepository.findAll()).thenReturn(transaccionList);
        TransaccionResponse response = transaccionService.obtenerTransacciones();

        assertEquals(1, response.getData().size());
        verify(transaccionRepository, times(1)).findAll();
    }

    @Test
    public void buscarTransaccionPorIdTest() {
        Transaccion transaccion = transaccionList.get(0);
        when(transaccionRepository.findById("1")).thenReturn(Optional.of(transaccion));

        TransaccionResponse response = transaccionService.buscarTransaccionPorId(1L);

        assertNotNull(response.getData());
        assertEquals(1L, response.getData().get(0).getId());
    }

    @Test
    public void registrarTransaccionTest() {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(1L);
        transaccion.setTipo("DEPOSITO");
        transaccion.setFechaPago(new Date());
        transaccion.setMonto(new BigDecimal("100"));
        transaccion.setNumeroCuentaDestino(1234567890);

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setId(Long.valueOf("1"));
        cuentaOrigen.setSaldo(new BigDecimal("500"));

        Cuenta cuentaDestino = new Cuenta();
        cuentaOrigen.setId(Long.valueOf("2"));
        cuentaDestino.setSaldo(new BigDecimal("300"));

        when(cuentaRepository.findById("1")).thenReturn(Optional.of(cuentaOrigen));
        when(cuentaRepository.findById("2")).thenReturn(Optional.of(cuentaDestino));
        when(transaccionRepository.save(any(Transaccion.class))).thenReturn(transaccion);

        TransaccionResponse response = transaccionService.registrarTransaccion(transaccion, 1L, 2L);

        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());
        assertEquals(new BigDecimal("400"), cuentaOrigen.getSaldo());
        assertEquals(new BigDecimal("400"), cuentaDestino.getSaldo());
        verify(transaccionRepository, times(1)).save(any(Transaccion.class));
        verify(cuentaRepository, times(1)).save(cuentaOrigen);
        verify(cuentaRepository, times(1)).save(cuentaDestino);
        
    }

    private void cargarTransacciones() {
        Transaccion transaccion1 = new Transaccion();
        transaccion1.setId(1L);
        transaccion1.setTipo("DEPOSITO");
        transaccion1.setFechaPago(new Date());
        transaccion1.setMonto(new BigDecimal("100"));
        transaccion1.setNumeroCuentaDestino(123456);

        transaccionList.add(transaccion1);
    }

}