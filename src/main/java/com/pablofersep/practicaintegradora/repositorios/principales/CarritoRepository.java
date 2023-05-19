package com.pablofersep.practicaintegradora.repositorios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Aviso;
import com.pablofersep.practicaintegradora.entidades.principales.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    Carrito findCarritoByCliente_Id(String email);
}
