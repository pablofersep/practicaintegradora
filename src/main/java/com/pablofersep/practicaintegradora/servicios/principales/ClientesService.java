package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Cliente;
import com.pablofersep.practicaintegradora.formobjects.cliente.ConsultaParametrizadaCliente;

import java.util.List;

public interface ClientesService {

    List<Cliente> consultaParametrizada(ConsultaParametrizadaCliente formObj);
    /**
     *
     * @param  c
     * @return Cliente si ha podido insertarse/modificarse. Nulo en caso de que haya alguna excepcion
     */
    Cliente crear_modificar(Cliente c);
    Cliente findById(String email);
}
