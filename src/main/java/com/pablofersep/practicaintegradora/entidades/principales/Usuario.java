package com.pablofersep.practicaintegradora.entidades.principales;

import com.pablofersep.practicaintegradora.entidades.auxiliares.Auditoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario {

    @Id
    private String email;
    @Column
    private String clave;
    @Column(name = "fecha_ultima_conexion")
    private LocalDate fechaUltimaConexion;
    @Column(name = "numero_accesos")
    private Integer numeroAccesos;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Cliente cliente;
    @Embedded
    private Auditoria auditoria;

}
