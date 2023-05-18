package com.pablofersep.practicaintegradora.entidades.principales;

import com.pablofersep.practicaintegradora.entidades.auxiliares.Auditoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Categoria {

    @Id
    private String codigo;
    @Column
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "categoria_padre", foreignKey = @ForeignKey(name = "FK_categoria_padre"))
    private Categoria categoriaPadre;
    @ManyToMany
    private Set<Categoria> categoriasHijas = new HashSet<>();
    @Embedded
    private Auditoria auditoria;

}
