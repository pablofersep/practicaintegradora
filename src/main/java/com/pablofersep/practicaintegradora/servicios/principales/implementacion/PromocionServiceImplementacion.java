package com.pablofersep.practicaintegradora.servicios.principales.implementacion;

import com.pablofersep.practicaintegradora.entidades.principales.Promocion;
import com.pablofersep.practicaintegradora.repositorios.principales.PromocionRepository;
import com.pablofersep.practicaintegradora.servicios.principales.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionServiceImplementacion implements PromocionService {
    @Autowired
    private PromocionRepository promocionRepository;


    @Override
    public List<Promocion> findAll() {
        return promocionRepository.findAll();
    }

    @Override
    public Promocion findById(Long id) {
        return promocionRepository.findPromocionById(id);
    }

    @Override
    public Promocion crear_modificar(Promocion p) {
        try {
            return promocionRepository.save(p);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Promocion p) {
        try{
            promocionRepository.delete(p);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
