package software.sistema.caja_ahorros.services;

import software.sistema.caja_ahorros.Controller.response.CreditoResponse;
import software.sistema.caja_ahorros.model.Credito;
import software.sistema.caja_ahorros.model.Cuenta;

public interface CreditoService {

    public CreditoResponse registrarCredito(Credito credito, Long idCuenta);
    public CreditoResponse actualizarCredito(Credito credito, Long idCuenta);

}