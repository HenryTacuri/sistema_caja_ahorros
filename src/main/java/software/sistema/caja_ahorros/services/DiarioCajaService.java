package software.sistema.caja_ahorros.services;
import software.sistema.caja_ahorros.Controller.response.DiarioCajaResponse;
import software.sistema.caja_ahorros.model.DiarioCaja;

public interface DiarioCajaService {

    public DiarioCajaResponse registrarDiarioCaja(DiarioCaja diarioCaja, Long idtransaccion);

    public DiarioCajaResponse actualizarDiarioCaja(DiarioCaja diarioCaja,  Long idtransaccion);

    public DiarioCajaResponse eliminarDiarioCaja(Integer id);

    public DiarioCajaResponse buscarDiarioCajaPorId(Integer id);

    public DiarioCajaResponse obtenerDiariosCaja();
}

