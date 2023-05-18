package com.pablofersep.practicaintegradora.repositorios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuntoCardinalRepository extends JpaRepository<PuntoCardinal, Long> {

    PuntoCardinal findPuntoCardinalBySiglasEquals(String siglas);
}