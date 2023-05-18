package com.pablofersep.practicaintegradora.servicios.principales.implementacion;

import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import com.pablofersep.practicaintegradora.repositorios.principales.CategoriaRepository;
import com.pablofersep.practicaintegradora.servicios.principales.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoriaServiceImplementacion implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public Categoria findByCodigo(String codigo) {
        return categoriaRepository.findCategoriaByCodigo(codigo);
    }

    @Override
    public boolean existenCategorias(List<String> codigos) {
        for(String s : codigos){
            if (findByCodigo(s) == null){
                return false;
            }
        }
        return true;
    }

    @Override
    public Set<Categoria> categoriasPorCodigos(List<String> codigos) {
        Set<Categoria> categorias = new HashSet<>();
        for(String s : codigos){
            categorias.add(findByCodigo(s));
        }
        return categorias;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
}