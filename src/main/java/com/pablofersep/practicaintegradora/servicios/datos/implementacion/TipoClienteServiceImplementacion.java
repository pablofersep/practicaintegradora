package com.pablofersep.practicaintegradora.servicios.datos.implementacion;

import com.pablofersep.practicaintegradora.entidades.datos.TipoCliente;
import com.pablofersep.practicaintegradora.entidades.principales.Cliente;
import com.pablofersep.practicaintegradora.repositorios.datos.TipoClienteRepository;
import com.pablofersep.practicaintegradora.servicios.datos.TipoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TipoClienteServiceImplementacion implements TipoClienteService {
    @Autowired
    private TipoClienteRepository tipoClienteRepository;
    @Override
    public String tipoClienteEnFuncionGasto(Cliente c) {
        List<TipoCliente> tiposCliente = tipoClienteRepository.findAll();
        TipoCliente result = new TipoCliente();
        result.setGastoUmbral(BigDecimal.valueOf(0));
        for (TipoCliente tc : tiposCliente){
            System.out.println(tc.getNombre());
            System.out.println(c.getGastoAcumuladoCliente());
            System.out.println(tc.getGastoUmbral());
            System.out.println(tc.getGastoUmbral().compareTo(c.getGastoAcumuladoCliente()));
            if (tc.getGastoUmbral().compareTo(c.getGastoAcumuladoCliente()) <= 0 ){
                if (tc.getGastoUmbral().compareTo(result.getGastoUmbral()) == 1 ){
                    result = tc;
                }
            }
        }
        return result.getNombre();
    }

    @Override
    public List<TipoCliente> findAll() {
        return tipoClienteRepository.findAll();
    }
}
