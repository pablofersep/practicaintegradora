package com.pablofersep.practicaintegradora.servicios.datos.implementacion;

import com.pablofersep.practicaintegradora.entidades.datos.EstadoPedido;
import com.pablofersep.practicaintegradora.repositorios.datos.EstadoPedidoRepository;
import com.pablofersep.practicaintegradora.servicios.datos.EstadoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoPedidoServiceImplementacion implements EstadoPedidoService {
    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;
    @Override
    public EstadoPedido findBySiglas(String siglas) {
        return estadoPedidoRepository.findEstadoPedidoBySiglasEquals(siglas);
    }

    @Override
    public List<EstadoPedido> findAll() {
        return estadoPedidoRepository.findAllByOrderByOrden();
    }
}
