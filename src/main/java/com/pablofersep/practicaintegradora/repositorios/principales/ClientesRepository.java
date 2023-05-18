package com.pablofersep.practicaintegradora.repositorios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {

    Cliente findClienteById(String id);

    List<Cliente> findClientesByApellidosContainsAndTipoClienteEqualsAndFechaNacimientoBetweenAndGastoAcumuladoClienteBetween(
            String apellidoPatter, String tipoCliente, LocalDate fechaIni, LocalDate fechaFin, BigDecimal gastoIni, BigDecimal gastoFin
    );

    List<Cliente> findClientesByApellidosContainsAndFechaNacimientoBetweenAndGastoAcumuladoClienteBetween(
            String apellidoPatter, LocalDate fechaIni, LocalDate fechaFin, BigDecimal gastoIni, BigDecimal gastoFin
    );

}
