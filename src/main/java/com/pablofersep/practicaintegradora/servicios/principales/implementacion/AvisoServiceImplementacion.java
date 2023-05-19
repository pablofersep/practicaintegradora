package com.pablofersep.practicaintegradora.servicios.principales.implementacion;

import com.pablofersep.practicaintegradora.entidades.principales.Aviso;
import com.pablofersep.practicaintegradora.repositorios.principales.AvisoRepository;
import com.pablofersep.practicaintegradora.servicios.principales.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisoServiceImplementacion implements AvisoService {
    @Autowired
    private AvisoRepository avisoRepository;

    @Override
    public Aviso crear_modificar(Aviso a) {
        try {
            return avisoRepository.save(a);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean borrar(Aviso a) {
        try {
            avisoRepository.delete(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Aviso> findAll() {
        return avisoRepository.findAllByOrderByFechaCreacion();
    }

    @Override
    public Aviso findById(Long id) {
        try {
            return avisoRepository.findAvisoByIdEquals(id);
        } catch (Exception e) {
            return null;
        }
    }
}
