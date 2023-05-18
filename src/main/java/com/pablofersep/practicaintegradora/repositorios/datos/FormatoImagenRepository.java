package com.pablofersep.practicaintegradora.repositorios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormatoImagenRepository extends JpaRepository<FormatoImagen, Long> {

    FormatoImagen findFormatoImagenBySiglasEquals(String siglas);
}