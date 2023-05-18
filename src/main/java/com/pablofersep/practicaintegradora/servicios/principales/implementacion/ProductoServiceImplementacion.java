package com.pablofersep.practicaintegradora.servicios.principales.implementacion;

import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import com.pablofersep.practicaintegradora.repositorios.principales.ProductoRepository;
import com.pablofersep.practicaintegradora.servicios.principales.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImplementacion implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto crear_modificar(Producto p) {
        try{
            return productoRepository.save(p);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Producto findProductoByCodigo(String codigo) {
        try{
            return productoRepository.findProductoByCodigo(codigo);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
}
