package software.sistema.caja_ahorros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.sistema.caja_ahorros.Controller.response.CreditoResponse;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.model.Credito;
import software.sistema.caja_ahorros.model.Cuenta;
import software.sistema.caja_ahorros.repositories.CreditoRepository;
import software.sistema.caja_ahorros.repositories.CuentaRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class CreditoServicesImpl implements CreditoService {

    @Autowired
    private CreditoRepository creditoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public CreditoResponse registrarCredito(Credito credito, Long idCuenta) {
        var creditoResponse = new CreditoResponse();
        var data = new ArrayList<Credito>();
        var infoList = new ArrayList<InfoRest>();

        var cuentaOptional = cuentaRepository.findById(String.valueOf(idCuenta));
        if (cuentaOptional.isEmpty()) {
            throw new IllegalArgumentException("La cuenta con ID " + idCuenta + " no existe.");
        }
        Cuenta cuenta = cuentaOptional.get();
        BigDecimal nuevoSaldo = cuenta.getSaldo().add(BigDecimal.valueOf(credito.getValor()));
        cuenta.setSaldo(nuevoSaldo);
        cuentaRepository.save(cuenta);
        credito.setCuenta(cuenta);
        creditoRepository.save(credito);
        data.add(credito);
        creditoResponse.setData(data);
        creditoResponse.setInfoList(infoList);
        return creditoResponse;
    }

    @Override
    public CreditoResponse actualizarCredito(Credito credito, Long idCuenta) {
        var creditoResponse = new CreditoResponse();
        var data = new ArrayList<Credito>();
        var infoList = new ArrayList<InfoRest>();

        // Buscar la cuenta asociada al ID
        var cuentaOptional = cuentaRepository.findById(String.valueOf(idCuenta));
        if (cuentaOptional.isEmpty()) {
            throw new IllegalArgumentException("La cuenta con ID " + idCuenta + " no existe.");
        }

        // Obtener la cuenta y asociarla al crédito
        Cuenta cuenta = cuentaOptional.get();
        credito.setCuenta(cuenta);

        // Actualizar el crédito
        data.add(this.creditoRepository.save(credito));

        // Configurar la respuesta
        creditoResponse.setData(data);
        creditoResponse.setInfoList(infoList);

        return creditoResponse;
    }

}
