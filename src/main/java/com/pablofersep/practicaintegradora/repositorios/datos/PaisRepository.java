package com.pablofersep.practicaintegradora.repositorios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {

    Pais findPaisBySiglasEquals(String siglas);
}