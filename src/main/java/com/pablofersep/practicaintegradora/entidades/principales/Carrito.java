package com.pablofersep.practicaintegradora.entidades.principales;

import com.pablofersep.practicaintegradora.entidades.auxiliares.LineaCarrito;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Carrito {


    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    @ElementCollection
    private Set<LineaCarrito> lineasCarrito = new HashSet<>();
    @Column
    private BigDecimal precio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_cliente_carrito"))
    private Cliente cliente;

}
