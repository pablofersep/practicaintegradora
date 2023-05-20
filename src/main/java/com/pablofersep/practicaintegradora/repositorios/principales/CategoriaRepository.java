package com.pablofersep.practicaintegradora.repositorios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findCategoriaByCodigo(String codigo);

}
