package software.sistema.caja_ahorros.services;

import software.sistema.caja_ahorros.Controller.response.CreditoResponse;
import software.sistema.caja_ahorros.model.Credito;

public interface CreditoService {

    public CreditoResponse registrarCredito(Credito credito, Long idCuenta);
    public CreditoResponse actualizarCredito(Credito cuenta, Long idCuenta);
    public CreditoResponse buscarCreditoPorId(Long idCuenta);
    public CreditoResponse obtenerCreditos();

}