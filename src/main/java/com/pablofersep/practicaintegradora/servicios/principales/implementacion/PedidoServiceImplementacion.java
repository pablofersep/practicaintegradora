package com.pablofersep.practicaintegradora.servicios.principales.implementacion;

import com.pablofersep.practicaintegradora.entidades.principales.Pedido;
import com.pablofersep.practicaintegradora.repositorios.principales.PedidoRepository;
import com.pablofersep.practicaintegradora.servicios.principales.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImplementacion implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Override
    public Pedido crear_modificar(Pedido pedido) {
        try{
            return pedidoRepository.save(pedido);
        }catch (Exception e){
            return null;
        }
    }
}