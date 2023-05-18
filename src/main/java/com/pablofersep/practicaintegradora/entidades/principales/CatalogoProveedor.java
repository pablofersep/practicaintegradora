package com.pablofersep.practicaintegradora.entidades.principales;

import com.pablofersep.practicaintegradora.entidades.auxiliares.LineaCatalogo;
import com.pablofersep.practicaintegradora.entidades.auxiliares.Periodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CatalogoProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_proveedor_catalogo"))
    private Proveedor proveedor;
    @Embedded
    private Periodo periodo;
    @ElementCollection
    private Set<LineaCatalogo> lineasCatalogo;

}
