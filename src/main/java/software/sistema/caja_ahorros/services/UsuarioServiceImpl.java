package software.sistema.caja_ahorros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.sistema.caja_ahorros.Controller.response.UsuarioResponse;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.model.Usuario;
import software.sistema.caja_ahorros.repositories.SocioRepository;
import software.sistema.caja_ahorros.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SocioRepository socioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UsuarioResponse registrarUsuario(Usuario usuario, Long idSocio) {
        var usuarioResponse = new UsuarioResponse();
        var data = new ArrayList<Usuario>();
        var infoList = new ArrayList<InfoRest>();
        var socioBuscado = this.socioRepository.findById(String.valueOf(idSocio));

        usuario.setSocio(socioBuscado.get());
        data.add(this.usuarioRepository.save(usuario));
        usuarioResponse.setData(data);
        usuarioResponse.setInfoList(infoList);

        return usuarioResponse;
    }

    @Override
    @Transactional
    public UsuarioResponse actualizarUsuario(Usuario usuario, Long idSocio) {
        var usuarioResponse = new UsuarioResponse();
        var data = new ArrayList<Usuario>();
        var infoList = new ArrayList<InfoRest>();
        var socioBuscado = this.socioRepository.findById(String.valueOf(idSocio));

        usuario.setSocio(socioBuscado.get());
        data.add(this.usuarioRepository.save(usuario));
        usuarioResponse.setData(data);
        usuarioResponse.setInfoList(infoList);

        return usuarioResponse;
    }

    @Override
    @Transactional
    public UsuarioResponse eliminarUsuario(Long id) {
        var usuarioResponse = new UsuarioResponse();
        var data = new ArrayList<Usuario>();
        var infoList = new ArrayList<InfoRest>();
        var usuarioBuscado = this.usuarioRepository.findById(String.valueOf(id));
        if(usuarioBuscado.isPresent()){
            this.usuarioRepository.deleteById(String.valueOf(id));
        }else{
            infoList.add(new InfoRest(1,"Usuario no encontrado",1));
        }
        usuarioResponse.setData(data);
        usuarioResponse.setInfoList(infoList);
        return usuarioResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse buscarUsuarioPorId(Long id) {
        var usuarioResponse = new UsuarioResponse();
        var data = new ArrayList<Usuario>();
        var infoList = new ArrayList<InfoRest>();
        var usuarioBuscado = this.usuarioRepository.findById(String.valueOf(id));
        if(usuarioBuscado.isPresent()){
            data.add(usuarioBuscado.get());
        }else{
            infoList.add(new InfoRest(1,"Usuario no encontrado",1));
        }
        usuarioResponse.setData(data);
        usuarioResponse.setInfoList(infoList);
        return usuarioResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse obtenerUsuarios() {
        var usuarioResponse = new UsuarioResponse();
        var data = (List<Usuario>) this.usuarioRepository.findAll();
        var infoList = new ArrayList<InfoRest>();
        usuarioResponse.setData(data);
        usuarioResponse.setInfoList(infoList);
        return usuarioResponse;
    }

    @Override
    @Transactional
    public UsuarioResponse login(String correo, String contrasenia) {
        var usuarioResponse = new UsuarioResponse();
        var data = new ArrayList<Usuario>();
        var infoList = new ArrayList<InfoRest>();
        var usuarioBuscado = this.usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia);

        if(usuarioBuscado.isPresent()){
            data.add(usuarioBuscado.get());
        }else{
            infoList.add(new InfoRest(1,"Usuario no encontrado",1));
        }
        usuarioResponse.setData(data);
        usuarioResponse.setInfoList(infoList);
        return usuarioResponse;
    }

}
