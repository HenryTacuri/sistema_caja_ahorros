package software.sistema.caja_ahorros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.sistema.caja_ahorros.Controller.response.CuentaResponse;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.Controller.response.UsuarioResponse;
import software.sistema.caja_ahorros.model.Cuenta;
import software.sistema.caja_ahorros.model.Usuario;
import software.sistema.caja_ahorros.repositories.CuentaRepository;
import software.sistema.caja_ahorros.repositories.SocioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Override
    public CuentaResponse registrarCuenta(Cuenta cuenta, Long idSocio) {
        var cuentaResponse = new CuentaResponse();
        var data = new ArrayList<Cuenta>();
        var infoList = new ArrayList<InfoRest>();
        var socioBuscado = this.socioRepository.findById(String.valueOf(idSocio));

        cuenta.setSocio(socioBuscado.get());
        data.add(this.cuentaRepository.save(cuenta));
        cuentaResponse.setData(data);
        cuentaResponse.setInfoList(infoList);

        return cuentaResponse;
    }

    @Override
    public CuentaResponse actualizarCuenta(Cuenta cuenta, Long idSocio) {
        var cuentaResponse = new CuentaResponse();
        var data = new ArrayList<Cuenta>();
        var infoList = new ArrayList<InfoRest>();
        var socioBuscado = this.socioRepository.findById(String.valueOf(idSocio));

        cuenta.setSocio(socioBuscado.get());
        data.add(this.cuentaRepository.save(cuenta));
        cuentaResponse.setData(data);
        cuentaResponse.setInfoList(infoList);

        return cuentaResponse;
    }

    @Override
    public CuentaResponse eliminarCuenta(Long id) {
        var cuentaResponse = new CuentaResponse();
        var data = new ArrayList<Cuenta>();
        var infoList = new ArrayList<InfoRest>();
        var cuentaBuscada = this.cuentaRepository.findById(String.valueOf(id));
        if(cuentaBuscada.isPresent()){
            this.cuentaRepository.deleteById(String.valueOf(id));
        }else{
            infoList.add(new InfoRest(1,"Cuenta no encontrada",1));
        }
        cuentaResponse.setData(data);
        cuentaResponse.setInfoList(infoList);
        return cuentaResponse;
    }

    @Override
    public CuentaResponse buscarCuentaPorId(Long id) {
        var cuentaResponse = new CuentaResponse();
        var data = new ArrayList<Cuenta>();
        var infoList = new ArrayList<InfoRest>();
        var cuentaBuscada = this.cuentaRepository.findById(String.valueOf(id));
        if(cuentaBuscada.isPresent()){
            data.add(cuentaBuscada.get());
        }else{
            infoList.add(new InfoRest(1,"Cuenta no encontrada",1));
        }
        cuentaResponse.setData(data);
        cuentaResponse.setInfoList(infoList);
        return cuentaResponse;
    }

    @Override
    public CuentaResponse obtenerCuentas() {
        var cuentaResponse = new CuentaResponse();
        var data = (List<Cuenta>) this.cuentaRepository.findAll();
        var infoList = new ArrayList<InfoRest>();
        cuentaResponse.setData(data);
        cuentaResponse.setInfoList(infoList);
        return cuentaResponse;
    }

}
