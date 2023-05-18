package com.pablofersep.practicaintegradora.entidades.principales;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String descripcion;
    @Column
    private String urgenciaAviso;
    @Column
    private LocalDate fechaProcesado;
    @ManyToOne
    @JoinColumn(name = "admin_procesa", foreignKey = @ForeignKey(name = "FK_usuario_aviso"))
    private Usuario usuarioAdminProcesa;

}
