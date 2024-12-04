package software.sistema.caja_ahorros.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import software.sistema.caja_ahorros.model.Cliente;
import software.sistema.caja_ahorros.services.ClienteService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping()
    public ResponseEntity<?> crearCliente(@Valid @RequestBody Cliente cliente) {
        boolean registroExitoso = clienteService.registrarCliente(cliente);

        Map<String, Object> json = new HashMap<>();

        if(registroExitoso) {
            json.put("code", HttpStatus.CREATED.value());
            json.put("message", "cliente creado exitosamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        } else {
            json.put("code", HttpStatus.CONFLICT.value());
            json.put("error", "El cliente ya existe");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
        }

    }

}
