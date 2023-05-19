package com.pablofersep.practicaintegradora.servicios.principales.implementacion;

import com.pablofersep.practicaintegradora.entidades.principales.Carrito;
import com.pablofersep.practicaintegradora.entidades.principales.Cliente;
import com.pablofersep.practicaintegradora.repositorios.principales.CarritoRepository;
import com.pablofersep.practicaintegradora.servicios.principales.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoServiceImplementacion implements CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    public Carrito findCarritoByEmailCliente(String cliente) {
        return carritoRepository.findCarritoByCliente_Id(cliente);
    }

    @Override
    public Carrito crear_modificar(Carrito c) {
        try{
            return carritoRepository.save(c);
        }catch (Exception e){
            return null;
        }
    }
}
