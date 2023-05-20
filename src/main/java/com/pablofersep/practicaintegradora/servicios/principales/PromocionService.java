package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Promocion;
import com.pablofersep.practicaintegradora.repositorios.principales.PromocionRepository;

import java.util.List;

public interface PromocionService {

    List<Promocion> findAll();

    Promocion findById(Long id);

    Promocion crear_modificar(Promocion p);

    boolean delete(Promocion p);
}
