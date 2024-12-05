package software.sistema.caja_ahorros.services;

import software.sistema.caja_ahorros.Controller.response.CuentaResponse;
import software.sistema.caja_ahorros.Controller.response.TransaccionResponse;
import software.sistema.caja_ahorros.model.Transaccion;

public interface TransaccionService {

    public TransaccionResponse registrarTransaccion(Transaccion transaccion, Long idCuentaOrigen, Long idCuentaDestino);

    public TransaccionResponse buscarTransaccionPorId(Long id);

    public TransaccionResponse obtenerTransacciones();

}
