package com.pablofersep.practicaintegradora.repositorios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Aviso;
import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvisoRepository extends JpaRepository<Aviso, Long> {

    Aviso findAvisoByIdEquals(Long id);
    List<Aviso> findAllByOrderByFechaCreacion();
}
