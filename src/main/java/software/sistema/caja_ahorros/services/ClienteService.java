package software.sistema.caja_ahorros.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.sistema.caja_ahorros.model.Cliente;
import software.sistema.caja_ahorros.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public boolean registrarCliente(Cliente cliente) {
        if(!clienteRepository.existsById(cliente.getCedula())) {
            clienteRepository.save(cliente);
            return true;
        } else {
            return false;
        }

    }

}
