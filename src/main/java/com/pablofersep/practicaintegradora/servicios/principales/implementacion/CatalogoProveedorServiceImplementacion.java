package com.pablofersep.practicaintegradora.servicios.principales.implementacion;

import com.pablofersep.practicaintegradora.entidades.principales.CatalogoProveedor;
import com.pablofersep.practicaintegradora.repositorios.principales.CatalogoProveedorRepository;
import com.pablofersep.practicaintegradora.servicios.principales.CatalogoProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatalogoProveedorServiceImplementacion implements CatalogoProveedorService {
    @Autowired
    private CatalogoProveedorRepository catalogoProveedorRepository;
    @Override
    public List<CatalogoProveedor> findAll() {
        return catalogoProveedorRepository.findAll();
    }

    @Override
    public CatalogoProveedor findById(Long id) {
        return catalogoProveedorRepository.findCatalogoProveedorByIdEquals(id);
    }
}
