package com.pablofersep.practicaintegradora.repositorios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto findProductoByCodigo(String codigo);
    List<Producto> findProductoByCategoriasContains(Categoria cat);

}
