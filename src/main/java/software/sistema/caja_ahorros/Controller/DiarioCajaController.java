package software.sistema.caja_ahorros.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.sistema.caja_ahorros.Controller.response.DiarioCajaResponse;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.model.DiarioCaja;
import software.sistema.caja_ahorros.services.DiarioCajaService;

import java.util.ArrayList;

@RestController
@RequestMapping("/diariocaja")
public class DiarioCajaController {

    @Autowired
    private DiarioCajaService diarioCajaService;

    @GetMapping()
    public ResponseEntity<DiarioCajaResponse> obtenerDiariosCaja() {
        try {
            var diarioCajaResponse = this.diarioCajaService.obtenerDiariosCaja();
            diarioCajaResponse.getInfoList().add(new InfoRest(1, "Respuesta Ok", 1));
            return new ResponseEntity<>(diarioCajaResponse, HttpStatus.OK);
        } catch (Exception e) {
            var diarioCajaResponse = new DiarioCajaResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2, e.getMessage(), 0));
            diarioCajaResponse.setInfoList(infoList);
            return new ResponseEntity<>(diarioCajaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiarioCajaResponse> buscarDiarioCajaPorId(@PathVariable Integer id) {
        try {
            var diarioCajaResponse = this.diarioCajaService.buscarDiarioCajaPorId(id);
            diarioCajaResponse.getInfoList().add(new InfoRest(1, "Respuesta Ok", 1));
            return new ResponseEntity<>(diarioCajaResponse, HttpStatus.OK);
        } catch (Exception e) {
            var diarioCajaResponse = new DiarioCajaResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2, e.getMessage(), 0));
            diarioCajaResponse.setInfoList(infoList);
            return new ResponseEntity<>(diarioCajaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{idtransaccion}")
    public ResponseEntity<DiarioCajaResponse> registrarDiarioCaja(@Valid @RequestBody DiarioCaja diarioCaja,@PathVariable Long idtransaccion) {
        try {
            var diarioCajaResponse = this.diarioCajaService.registrarDiarioCaja(diarioCaja, idtransaccion);
            diarioCajaResponse.getInfoList().add(new InfoRest(1, "Respuesta Ok", 1));
            return new ResponseEntity<>(diarioCajaResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            var diarioCajaResponse = new DiarioCajaResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2, e.getMessage(), 0));
            diarioCajaResponse.setInfoList(infoList);
            return new ResponseEntity<>(diarioCajaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idtransaccion}")
    public ResponseEntity<DiarioCajaResponse> actualizarDiarioCaja(@RequestBody DiarioCaja diarioCaja,@PathVariable Long idtransaccion) {
        try {
            var diarioCajaResponse = this.diarioCajaService.actualizarDiarioCaja(diarioCaja, idtransaccion);
            diarioCajaResponse.getInfoList().add(new InfoRest(1, "Respuesta Ok", 1));
            return new ResponseEntity<>(diarioCajaResponse, HttpStatus.OK);
        } catch (Exception e) {
            var diarioCajaResponse = new DiarioCajaResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2, e.getMessage(), 0));
            diarioCajaResponse.setInfoList(infoList);
            return new ResponseEntity<>(diarioCajaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DiarioCajaResponse> eliminarDiarioCaja(@PathVariable Integer id) {
        try {
            var diarioCajaResponse = this.diarioCajaService.eliminarDiarioCaja(id);
            diarioCajaResponse.getInfoList().add(new InfoRest(1, "Respuesta Ok", 1));
            return new ResponseEntity<>(diarioCajaResponse, HttpStatus.OK);
        } catch (Exception e) {
            var diarioCajaResponse = new DiarioCajaResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2, e.getMessage(), 0));
            diarioCajaResponse.setInfoList(infoList);
            return new ResponseEntity<>(diarioCajaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
