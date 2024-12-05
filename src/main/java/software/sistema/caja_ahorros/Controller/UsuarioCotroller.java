package software.sistema.caja_ahorros.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.sistema.caja_ahorros.Controller.response.SocioResponse;
import software.sistema.caja_ahorros.Controller.response.TransaccionResponse;
import software.sistema.caja_ahorros.Controller.response.UsuarioResponse;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.model.Socio;
import software.sistema.caja_ahorros.model.Usuario;
import software.sistema.caja_ahorros.services.UsuarioService;

import java.util.ArrayList;

@RestController
@RequestMapping("/usuarios")
public class UsuarioCotroller {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<UsuarioResponse> obtenerUsuarios() {
        try{
            var usuarioResponse = this.usuarioService.obtenerUsuarios();
            usuarioResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
        }catch(Exception e1){
            var usuarioResponse = new UsuarioResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            usuarioResponse.setInfoList(infoList);
            return new ResponseEntity<>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(@PathVariable Long id){
        try{
            var usuarioResponse = this.usuarioService.buscarUsuarioPorId(id);
            usuarioResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
        }catch(Exception e1){
            var usuarioResponse = new UsuarioResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            usuarioResponse.setInfoList(infoList);
            return new ResponseEntity<>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{idSocio}")
    public ResponseEntity<UsuarioResponse> registrarUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Long idSocio) {
        try{
            var usuarioResponse = this.usuarioService.registrarUsuario(usuario, idSocio);
            usuarioResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
        }catch(Exception e1){
            var usuarioResponse = new UsuarioResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            usuarioResponse.setInfoList(infoList);
            return new ResponseEntity<>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{idSocio}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long idSocio) {
        try{
            var usuarioResponse = this.usuarioService.actualizarUsuario(usuario, idSocio);
            usuarioResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
        }catch(Exception e1){
            var usuarioResponse = new UsuarioResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            usuarioResponse.setInfoList(infoList);
            return new ResponseEntity<>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponse> eliminarUsuario(@PathVariable Long id){
        try{
            var usuarioResponse = this.usuarioService.eliminarUsuario(id);
            usuarioResponse.getInfoList().add(new InfoRest(1,"Respuesta Ok",1));
            return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
        }catch(Exception e1){
            var usuarioResponse = new UsuarioResponse();
            var infoList = new ArrayList<InfoRest>();
            infoList.add(new InfoRest(2,e1.getMessage(),0));
            usuarioResponse.setInfoList(infoList);
            return new ResponseEntity<>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
