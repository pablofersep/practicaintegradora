package com.pablofersep.practicaintegradora.servicios.principales.implementacion;

import com.pablofersep.practicaintegradora.entidades.principales.Proveedor;
import com.pablofersep.practicaintegradora.repositorios.principales.ProveedorRepository;
import com.pablofersep.practicaintegradora.servicios.principales.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImplementacion implements ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor findById(Long id) {
        return proveedorRepository.findProveedorByIdEquals(id);
    }
}
