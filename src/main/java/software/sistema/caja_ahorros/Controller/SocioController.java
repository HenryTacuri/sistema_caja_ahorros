package software.sistema.caja_ahorros.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.Controller.response.SocioResponse;
import software.sistema.caja_ahorros.model.Socio;
import software.sistema.caja_ahorros.services.SocioService;

import java.util.ArrayList;

@RestController
@RequestMapping("/socios")
public class SocioController {

    @Autowired
    private SocioService socioService;

    @GetMapping()
    public ResponseEntity<SocioResponse> obtenerSocios() {
        try{
            var socioResponse = this.socioService.obtenerSocios();
            socioResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(socioResponse, HttpStatus.OK);
        }catch(Exception e1){
            var socioResponse = new SocioResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            socioResponse.setInfoList(infoList);
            return new ResponseEntity<>(socioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<SocioResponse> buscarSocioPorId(@PathVariable Long id){
        try{
            var socioResponse = this.socioService.buscarSocioPorId(id);
            socioResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(socioResponse, HttpStatus.OK);
        }catch(Exception e1){
            var socioResponse = new SocioResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            socioResponse.setInfoList(infoList);
            return new ResponseEntity<>(socioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<SocioResponse> registrarSocio(@Valid @RequestBody Socio socio) {
        try{
            var socioResponse = this.socioService.registrarSocio(socio);
            socioResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(socioResponse, HttpStatus.CREATED);
        }catch(Exception e1){
            var socioResponse = new SocioResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            socioResponse.setInfoList(infoList);
            return new ResponseEntity<>(socioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping()
    public ResponseEntity<SocioResponse> actualizarSocio(@RequestBody Socio socio){
        try{
            var socioResponse = this.socioService.actualizarSocio(socio);
            socioResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(socioResponse, HttpStatus.OK);
        }catch(Exception e1){
            var socioResponse = new SocioResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            socioResponse.setInfoList(infoList);
            return new ResponseEntity<>(socioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SocioResponse> eliminarSocio(@PathVariable Long id){
        try{
            var socioResponse = this.socioService.eliminarSocio(id);
            socioResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(socioResponse, HttpStatus.OK);
        }catch(Exception e1){
            var socioResponse = new SocioResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            socioResponse.setInfoList(infoList);
            return new ResponseEntity<>(socioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
