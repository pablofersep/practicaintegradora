package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Aviso;

import java.util.List;

public interface AvisoService {

    Aviso crear_modificar(Aviso a);
    boolean borrar(Aviso a);
    List<Aviso> findAll();
    Aviso findById(Long id);
}
