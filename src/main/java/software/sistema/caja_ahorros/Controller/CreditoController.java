package software.sistema.caja_ahorros.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import software.sistema.caja_ahorros.Controller.response.CreditoResponse;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.model.Credito;
import software.sistema.caja_ahorros.services.CreditoService;

@RestController
@RequestMapping("/creditos")
public class CreditoController {
    @Autowired
    private CreditoService creditoService;

    @GetMapping()
    public ResponseEntity<CreditoResponse> obtenerCreditos() {
        try {
            var creditoResponse = this.creditoService.obtenerCreditos();
            creditoResponse.getInfoList().add(new InfoRest(1, "Respuesta Ok", 1));
            return new ResponseEntity<>(creditoResponse, HttpStatus.OK);
        } catch (Exception e1) {
            var creditoResponse = new CreditoResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2, e1.getMessage(), 0));
            creditoResponse.setInfoList(infoList);
            return new ResponseEntity<>(creditoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<CreditoResponse> buscarCreditoPorId(@PathVariable Long id) {
        try {
            var creditoResponse = this.creditoService.buscarCreditoPorId(id);
            creditoResponse.getInfoList().add(new InfoRest(1, "Respuesta Ok", 1));
            return new ResponseEntity<>(creditoResponse, HttpStatus.OK);
        } catch (Exception e1) {
            var creditoResponse = new CreditoResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2, e1.getMessage(), 0));
            creditoResponse.setInfoList(infoList);
            return new ResponseEntity<>(creditoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
