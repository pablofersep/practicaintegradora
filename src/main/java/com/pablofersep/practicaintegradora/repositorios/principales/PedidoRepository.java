package com.pablofersep.practicaintegradora.repositorios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Pedido;
import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findPedidoByIdEquals(Long id);
}
