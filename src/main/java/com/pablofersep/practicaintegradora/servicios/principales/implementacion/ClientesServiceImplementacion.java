package com.pablofersep.practicaintegradora.servicios.principales.implementacion;

import com.pablofersep.practicaintegradora.entidades.principales.Cliente;
import com.pablofersep.practicaintegradora.formobjects.cliente.ConsultaParametrizadaCliente;
import com.pablofersep.practicaintegradora.repositorios.principales.ClientesRepository;
import com.pablofersep.practicaintegradora.servicios.principales.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientesServiceImplementacion  implements ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Override
    public List<Cliente> findAll() {
        return clientesRepository.findAll();
    }

    @Override
    public List<Cliente> consultaParametrizada(ConsultaParametrizadaCliente formObj) {
        if (formObj.getFechaIni() == null)formObj.setFechaIni(LocalDate.MIN);
        if (formObj.getFechaFin() == null)formObj.setFechaFin(LocalDate.now());
        if (formObj.getGastoFin() == null)formObj.setGastoFin(BigDecimal.valueOf(99999.99));
        if (formObj.getGastoIni() == null)formObj.setGastoIni(BigDecimal.valueOf(0));
        List<Cliente> clientes = new ArrayList<>();
        if (formObj.getTipoCliente().equals("All")){
            clientes = clientesRepository.findClientesByApellidosContainsAndFechaNacimientoBetweenAndGastoAcumuladoClienteBetween(
                    formObj.getApellidoPattern(), formObj.getFechaIni(), formObj.getFechaFin(), formObj.getGastoIni(), formObj.getGastoFin()
            );
            return clientes;
        }
        clientes = clientesRepository.findClientesByApellidosContainsAndTipoClienteEqualsAndFechaNacimientoBetweenAndGastoAcumuladoClienteBetween(
                formObj.getApellidoPattern(), formObj.getTipoCliente(), formObj.getFechaIni(), formObj.getFechaFin(), formObj.getGastoIni(), formObj.getGastoFin()
        );
        return clientes;
    }

    @Override
    public Cliente crear_modificar(Cliente c) {
        try{
            return clientesRepository.save(c);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Cliente findById(String email) {
        return clientesRepository.findClienteById(email);
    }
}
