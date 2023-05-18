package com.pablofersep.practicaintegradora.entidades.principales;

import com.pablofersep.practicaintegradora.entidades.auxiliares.*;
import com.pablofersep.practicaintegradora.entidades.datos.Pais;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cliente {

    @Id
    private String id;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_usuario_cliente"))
    private Usuario usuario;
    @Column
    private String genero;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @ManyToOne
    @JoinColumn(name = "pais_nacimiento", foreignKey = @ForeignKey(name = "FK_pais_cliente"))
    private Pais paisNacimiento;
    @Column(name = "tipo_documento_cliente")
    private String tipoDocumentoCliente;
    @Column
    private String documento;
    @Column(name = "telefono_movil")
    private String telefonoMovil;
    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Embedded
    private Direccion direccion;
    @ElementCollection
    private Set<Direccion> direccionesEntrega;
    @ElementCollection
    private Set<TarjetaCredito> tarjetasCredito;
    @Column
    private BigDecimal gastoAcumuladoCliente;
    @Column(name = "tipo_cliente")
    private String tipoCliente;
    @OneToMany
    private Set<Categoria> categoriasInteres;
    @Column
    private String comentarios;
    @Column(name = "aceptacion_licencia")
    private Boolean aceptacionLicencia;
    @Embedded
    private Auditoria auditoria;

}
