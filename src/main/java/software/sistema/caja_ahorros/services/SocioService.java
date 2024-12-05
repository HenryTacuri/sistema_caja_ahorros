package software.sistema.caja_ahorros.services;

import software.sistema.caja_ahorros.Controller.response.SocioResponse;
import software.sistema.caja_ahorros.model.Socio;

public interface SocioService {

    public SocioResponse registrarSocio(Socio socio);

    public SocioResponse actualizarSocio(Socio socio);

    public SocioResponse eliminarSocio(Long id);

    public SocioResponse buscarSocioPorId(Long id);

    public SocioResponse obtenerSocios();

}
