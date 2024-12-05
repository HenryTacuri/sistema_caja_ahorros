package software.sistema.caja_ahorros.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.Controller.response.TransaccionResponse;
import software.sistema.caja_ahorros.model.Transaccion;
import software.sistema.caja_ahorros.repositories.CuentaRepository;
import software.sistema.caja_ahorros.repositories.TransaccionRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class TransaccionServiceImpl implements TransaccionService{
    @Autowired
    private TransaccionRepository transaccionRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Override
    public TransaccionResponse registrarTransaccion(Transaccion transaccion, Long idCuentaOrigen, Long idCuentaDestino) {
        var transaccionResponse = new TransaccionResponse();
        var data = new ArrayList<Transaccion>();
        var infoList = new ArrayList<InfoRest>();
        var cuentaOrigen = this.cuentaRepository.findById(String.valueOf(idCuentaOrigen));
        var cuentaDestino = this.cuentaRepository.findById(String.valueOf(idCuentaDestino));
        transaccion.setCuenta(cuentaOrigen.get());
        transaccion.setFechaPago(new Date());
        transaccion.setNumeroCuentaDestino(cuentaDestino.get().getNumeroCuenta());
        data.add(this.transaccionRepository.save(transaccion));
        //Actualizamos el saldo de la cuenta de origen
        BigDecimal nuevoSaldo = cuentaOrigen.get().getSaldo().subtract(transaccion.getMonto());
        cuentaOrigen.get().setSaldo(nuevoSaldo);
        //Actualizamos el saldo de la cuenta de destino
        nuevoSaldo = cuentaDestino.get().getSaldo().add(transaccion.getMonto());
        cuentaDestino.get().setSaldo(nuevoSaldo);
        this.cuentaRepository.save(cuentaOrigen.get());
        this.cuentaRepository.save(cuentaDestino.get());
        transaccionResponse.setData(data);
        transaccionResponse.setInfoList(infoList);
        return transaccionResponse;
    }
    @Override
    public TransaccionResponse buscarTransaccionPorId(Long id) {
        var transaccionResponse = new TransaccionResponse();
        var data = new ArrayList<Transaccion>();
        var infoList = new ArrayList<InfoRest>();
        var transaccionBuscada = this.transaccionRepository.findById(String.valueOf(id));
        if(transaccionBuscada.isPresent()){
            data.add(transaccionBuscada.get());
        }else{
            infoList.add(new InfoRest(1,"Transaccion no encontrada",1));
        }
        transaccionResponse.setData(data);
        transaccionResponse.setInfoList(infoList);
        return transaccionResponse;
    }
    @Override
    public TransaccionResponse obtenerTransacciones() {
        var transaccionResponse = new TransaccionResponse();
        var data = (List<Transaccion>) this.transaccionRepository.findAll();
        var infoList = new ArrayList<InfoRest>();
        transaccionResponse.setData(data);
        transaccionResponse.setInfoList(infoList);
        return transaccionResponse;
    }
}