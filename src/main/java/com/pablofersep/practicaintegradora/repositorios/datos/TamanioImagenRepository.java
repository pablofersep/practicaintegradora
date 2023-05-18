package com.pablofersep.practicaintegradora.repositorios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TamanioImagenRepository extends JpaRepository<TamanioImagen, Long> {

    TamanioImagen findTamanioImagenBySiglasEquals(String siglas);
}