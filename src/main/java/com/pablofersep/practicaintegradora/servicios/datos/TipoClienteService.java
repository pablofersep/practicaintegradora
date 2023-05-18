package com.pablofersep.practicaintegradora.servicios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.TipoCliente;
import com.pablofersep.practicaintegradora.entidades.principales.Cliente;

import java.util.List;

public interface TipoClienteService {

    String tipoClienteEnFuncionGasto(Cliente c);

    List<TipoCliente> findAll();
}
