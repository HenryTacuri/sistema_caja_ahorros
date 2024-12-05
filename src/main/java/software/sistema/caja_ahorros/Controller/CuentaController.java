package software.sistema.caja_ahorros.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.sistema.caja_ahorros.Controller.response.CuentaResponse;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.Controller.response.SocioResponse;
import software.sistema.caja_ahorros.model.Cuenta;
import software.sistema.caja_ahorros.model.Socio;
import software.sistema.caja_ahorros.services.CuentaService;

import java.util.ArrayList;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;


    @GetMapping()
    public ResponseEntity<CuentaResponse> obtenerCuentas() {
        try{
            var cuentaResponse = this.cuentaService.obtenerCuentas();
            cuentaResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(cuentaResponse, HttpStatus.OK);
        }catch(Exception e1){
            var cuentaResponse = new CuentaResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            cuentaResponse.setInfoList(infoList);
            return new ResponseEntity<>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<CuentaResponse> buscarCuentaPorId(@PathVariable Long id){
        try{
            var cuentaResponse = this.cuentaService.buscarCuentaPorId(id);
            cuentaResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(cuentaResponse, HttpStatus.OK);
        }catch(Exception e1){
            var cuentaResponse = new CuentaResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            cuentaResponse.setInfoList(infoList);
            return new ResponseEntity<>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{idSocio}")
    public ResponseEntity<CuentaResponse> registrarCuenta(@Valid @RequestBody Cuenta cuenta, @PathVariable Long idSocio) {
        try{
            var cuentaResponse = this.cuentaService.registrarCuenta(cuenta, idSocio);
            cuentaResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(cuentaResponse, HttpStatus.CREATED);
        }catch(Exception e1){
            var cuentaResponse = new CuentaResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            cuentaResponse.setInfoList(infoList);
            return new ResponseEntity<>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{idSocio}")
    public ResponseEntity<CuentaResponse> actualizarCuenta(@RequestBody Cuenta cuenta, @PathVariable Long idSocio) {
        try{
            var cuentaResponse = this.cuentaService.actualizarCuenta(cuenta, idSocio);
            cuentaResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(cuentaResponse, HttpStatus.OK);
        }catch(Exception e1){
            var cuentaResponse = new CuentaResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            cuentaResponse.setInfoList(infoList);
            return new ResponseEntity<>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CuentaResponse> eliminarCuenta(@PathVariable Long id){
        try{
            var cuentaResponse = this.cuentaService.eliminarCuenta(id);
            cuentaResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(cuentaResponse, HttpStatus.OK);
        }catch(Exception e1){
            var cuentaResponse = new CuentaResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            cuentaResponse.setInfoList(infoList);
            return new ResponseEntity<>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
