package software.sistema.caja_ahorros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.sistema.caja_ahorros.Controller.response.InfoRest;
import software.sistema.caja_ahorros.Controller.response.SocioResponse;
import software.sistema.caja_ahorros.model.Socio;
import software.sistema.caja_ahorros.repositories.SocioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocioServiceImpl implements SocioService {

    @Autowired
    private SocioRepository socioRepository;

    public SocioServiceImpl(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    @Override
    @Transactional
    public SocioResponse registrarSocio(Socio socio) {
        var socioResponse = new SocioResponse();
        var data = new ArrayList<Socio>();
        var infoList = new ArrayList<InfoRest>();

        data.add(this.socioRepository.save(socio));
        socioResponse.setData(data);
        socioResponse.setInfoList(infoList);

        return socioResponse;
    }

    @Override
    @Transactional
    public SocioResponse actualizarSocio(Socio socio) {
        var socioResponse = new SocioResponse();
        var data = new ArrayList<Socio>();
        var infoList = new ArrayList<InfoRest>();

        data.add(this.socioRepository.save(socio));
        socioResponse.setData(data);
        socioResponse.setInfoList(infoList);

        return socioResponse;
    }

    @Override
    @Transactional
    public SocioResponse eliminarSocio(Long id) {
        var socioResponse = new SocioResponse();
        var data = new ArrayList<Socio>();
        var infoList = new ArrayList<InfoRest>();
        var socioBuscado = this.socioRepository.findById(String.valueOf(id));
        if(socioBuscado.isPresent()){
            this.socioRepository.deleteById(String.valueOf(id));
        }else{
            infoList.add(new InfoRest(1,"Socio no encontrado",1));
        }
        socioResponse.setData(data);
        socioResponse.setInfoList(infoList);
        return socioResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public SocioResponse buscarSocioPorId(Long id) {
        var socioResponse = new SocioResponse();
        var data = new ArrayList<Socio>();
        var infoList = new ArrayList<InfoRest>();
        var socioBuscado = this.socioRepository.findById(String.valueOf(id));
        if(socioBuscado.isPresent()){
            data.add(socioBuscado.get());
        }else{
            infoList.add(new InfoRest(1,"Usuario no encontrado",1));
        }
        socioResponse.setData(data);
        socioResponse.setInfoList(infoList);
        return socioResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public SocioResponse obtenerSocios() {
        var socioResponse = new SocioResponse();
        var data = (List<Socio>) this.socioRepository.findAll();
        var infoList = new ArrayList<InfoRest>();
        socioResponse.setData(data);
        socioResponse.setInfoList(infoList);
        return socioResponse;
    }
}
