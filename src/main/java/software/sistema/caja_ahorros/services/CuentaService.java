package software.sistema.caja_ahorros.services;
import software.sistema.caja_ahorros.Controller.response.CuentaResponse;
import software.sistema.caja_ahorros.model.Cuenta;
public interface CuentaService {
    public CuentaResponse registrarCuenta(Cuenta cuenta, Long idSocio);
    public CuentaResponse actualizarCuenta(Cuenta cuenta, Long idSocio);
    public CuentaResponse eliminarCuenta(Long id);
    public CuentaResponse buscarCuentaPorId(Long id);
    public CuentaResponse obtenerCuentas();
}