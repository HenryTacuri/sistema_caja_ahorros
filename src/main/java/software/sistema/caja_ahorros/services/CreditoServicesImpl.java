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
        var cuentaOptional = cuentaRepository.findById(String.valueOf(idCuenta));
        if (cuentaOptional.isEmpty()) {
            throw new IllegalArgumentException("La cuenta con ID " + idCuenta + " no existe.");
        }
        Cuenta cuenta = cuentaOptional.get();
        credito.setCuenta(cuenta);
        data.add(this.creditoRepository.save(credito));
        creditoResponse.setData(data);
        creditoResponse.setInfoList(infoList);

        return creditoResponse;
    }

    @Override
    public CreditoResponse buscarCreditoPorId(Long idCuenta) {
        // Crear objeto de respuesta
        var creditoResponse = new CreditoResponse();
        var data = new ArrayList<Credito>();
        var infoList = new ArrayList<InfoRest>();

        // Buscar la cuenta por su ID
        var cuentaOptional = cuentaRepository.findById(String.valueOf(idCuenta));
        if (cuentaOptional.isEmpty()) {
            throw new IllegalArgumentException("La cuenta con ID " + idCuenta + " no existe.");
        }

        // Obtener la cuenta encontrada
        Cuenta cuenta = cuentaOptional.get();

        // Buscar el crédito asociado a la cuenta
        var creditoOptional = creditoRepository.findById(String.valueOf(idCuenta));
        if (creditoOptional.isEmpty()) {
            throw new IllegalArgumentException("No se encontró crédito asociado a la cuenta con ID " + idCuenta);
        }

        // Obtener el crédito encontrado
        Credito credito = creditoOptional.get();

        // Agregar el crédito a la lista de datos de la respuesta
        data.add(credito);

        // Establecer los datos y la información adicional en la respuesta
        creditoResponse.setData(data);
        creditoResponse.setInfoList(infoList);

        return creditoResponse;
    }


    @Override
    public CreditoResponse obtenerCreditos() {
        var creditoResponse = new CreditoResponse();
        var data = new ArrayList<Credito>();
        var infoList = new ArrayList<InfoRest>();
        var creditos = this.creditoRepository.findAll(); 
        data.addAll(creditos); 
        creditoResponse.setData(data);
        creditoResponse.setInfoList(infoList);
        return creditoResponse;
    }

}
