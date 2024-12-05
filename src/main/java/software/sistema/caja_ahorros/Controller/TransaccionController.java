package software.sistema.caja_ahorros.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.sistema.caja_ahorros.Controller.response.CuentaResponse;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.Controller.response.TransaccionResponse;
import software.sistema.caja_ahorros.model.Transaccion;
import software.sistema.caja_ahorros.services.TransaccionService;

import java.util.ArrayList;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping()
    public ResponseEntity<TransaccionResponse> obtenerTransacciones() {
        try{
            var transaccionResponse = this.transaccionService.obtenerTransacciones();
            transaccionResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(transaccionResponse, HttpStatus.OK);
        }catch(Exception e1){
            var transaccionResponse = new TransaccionResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            transaccionResponse.setInfoList(infoList);
            return new ResponseEntity<>(transaccionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<TransaccionResponse> buscarTransaccionPorId(@PathVariable Long id){
        try{
            var transaccionResponse = this.transaccionService.buscarTransaccionPorId(id);
            transaccionResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(transaccionResponse, HttpStatus.OK);
        }catch(Exception e1){
            var transaccionResponse = new TransaccionResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            transaccionResponse.setInfoList(infoList);
            return new ResponseEntity<>(transaccionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{idCuentaOrigen}/{idCuentaDestino}")
    public ResponseEntity<TransaccionResponse> registrarTransaccion(@Valid @RequestBody Transaccion transaccion, @PathVariable Long idCuentaOrigen, @PathVariable Long idCuentaDestino) {

        try{
            var transaccionResponse = this.transaccionService.registrarTransaccion(transaccion, idCuentaDestino, idCuentaOrigen);
            transaccionResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(transaccionResponse, HttpStatus.CREATED);
        }catch(Exception e1){
            var transaccionResponse = new TransaccionResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            transaccionResponse.setInfoList(infoList);
            return new ResponseEntity<>(transaccionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
