package software.sistema.caja_ahorros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software.sistema.caja_ahorros.Controller.response.CuentaResponse;
import software.sistema.caja_ahorros.Controller.response.DiarioCajaResponse;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.model.Cuenta;
import software.sistema.caja_ahorros.model.DiarioCaja;
import software.sistema.caja_ahorros.repositories.DiarioCajaRepository;
import software.sistema.caja_ahorros.repositories.TransaccionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiarioCajaServiceImpl implements DiarioCajaService {

    @Autowired
    private DiarioCajaRepository diarioCajaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Override
    public DiarioCajaResponse registrarDiarioCaja(DiarioCaja diarioCaja, Long idtransaccion) {
        var diarioCajaResponse = new DiarioCajaResponse();
        var data = new ArrayList<DiarioCaja>();
        var infoList = new ArrayList<InfoRest>();
        var transaccionBuscada = this.transaccionRepository.findById(String.valueOf(idtransaccion));

        diarioCaja.setInTransaccion(transaccionBuscada.get());
        data.add(this.diarioCajaRepository.save(diarioCaja));
        diarioCajaResponse.setData(data);
        diarioCajaResponse.setInfoList(infoList);

        return diarioCajaResponse;
    }

    @Override
    public DiarioCajaResponse actualizarDiarioCaja(DiarioCaja diarioCaja, Long idtransaccion) {
        var diarioCajaResponse = new DiarioCajaResponse();
        var data = new ArrayList<DiarioCaja>();
        var infoList = new ArrayList<InfoRest>();
        
        var transaccionBuscada = this.transaccionRepository.findById(String.valueOf(idtransaccion));

        Optional<DiarioCaja> diarioExistente = this.diarioCajaRepository.findById(diarioCaja.getId());
        if (diarioExistente.isPresent()) {
            data.add(this.diarioCajaRepository.save(diarioCaja));
            diarioCaja.setInTransaccion(transaccionBuscada.get());
            infoList.add(new InfoRest(1, "Diario de Caja actualizado exitosamente", 1));
        } else {
            infoList.add(new InfoRest(2, "Diario de Caja no encontrado", 0));
        }

        diarioCajaResponse.setData(data);
        diarioCajaResponse.setInfoList(infoList);
        return diarioCajaResponse;
    }

    @Override
    public DiarioCajaResponse eliminarDiarioCaja(Integer id) {
        var diarioCajaResponse = new DiarioCajaResponse();
        var data = new ArrayList<DiarioCaja>();
        var infoList = new ArrayList<InfoRest>();

        Optional<DiarioCaja> diarioExistente = this.diarioCajaRepository.findById(id);
        if (diarioExistente.isPresent()) {
            this.diarioCajaRepository.deleteById(id);
            infoList.add(new InfoRest(1, "Diario de Caja eliminado exitosamente", 1));
        } else {
            infoList.add(new InfoRest(2, "Diario de Caja no encontrado", 0));
        }

        diarioCajaResponse.setData(data);
        diarioCajaResponse.setInfoList(infoList);
        return diarioCajaResponse;
    }

    @Override
    public DiarioCajaResponse buscarDiarioCajaPorId(Integer id) {
        var diarioCajaResponse = new DiarioCajaResponse();
        var data = new ArrayList<DiarioCaja>();
        var infoList = new ArrayList<InfoRest>();

        Optional<DiarioCaja> diarioCaja = this.diarioCajaRepository.findById(id);
        if (diarioCaja.isPresent()) {
            data.add(diarioCaja.get());
        } else {
            infoList.add(new InfoRest(2, "Diario de Caja no encontrado", 0));
        }

        diarioCajaResponse.setData(data);
        diarioCajaResponse.setInfoList(infoList);
        return diarioCajaResponse;
    }

    @Override
    public DiarioCajaResponse obtenerDiariosCaja() {
        var diarioCajaResponse = new DiarioCajaResponse();
        var data = (List<DiarioCaja>) this.diarioCajaRepository.findAll();
        var infoList = new ArrayList<InfoRest>();

        infoList.add(new InfoRest(1, "Listado de Diarios de Caja obtenido correctamente", 1));

        diarioCajaResponse.setData(data);
        diarioCajaResponse.setInfoList(infoList);
        return diarioCajaResponse;
    }
}
