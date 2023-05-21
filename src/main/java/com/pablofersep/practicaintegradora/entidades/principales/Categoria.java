package com.pablofersep.practicaintegradora.entidades.principales;

import com.pablofersep.practicaintegradora.entidades.auxiliares.Auditoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

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
    @JoinColumn(name = "categoria_padre")
    private Categoria categoriaPadre;
    @OneToMany(mappedBy = "categoriaPadre")
    private Set<Categoria> categoriasHijas;
    @Embedded
    private Auditoria auditoria;

}
