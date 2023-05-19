package com.pablofersep.practicaintegradora.servicios.datos.implementacion;

import com.pablofersep.practicaintegradora.entidades.datos.UrgenciaAviso;
import com.pablofersep.practicaintegradora.repositorios.datos.UrgenciaAvisoRepository;
import com.pablofersep.practicaintegradora.repositorios.principales.UsuarioRepository;
import com.pablofersep.practicaintegradora.servicios.datos.UrgenciaAvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UrgenciaAvisoServiceImplementacion implements UrgenciaAvisoService {
    @Autowired
    private UrgenciaAvisoRepository urgenciaAvisoRepository;
    @Override
    public List<UrgenciaAviso> findAll() {
        return urgenciaAvisoRepository.findAll();
    }

    @Override
    public String getUrgenciaByCodigo(String codigo) {
        return urgenciaAvisoRepository.findUrgenciaAvisoBySiglasEquals(codigo).getNombre();
    }
}
