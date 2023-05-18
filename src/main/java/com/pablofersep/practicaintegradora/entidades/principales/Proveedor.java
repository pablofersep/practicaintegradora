package com.pablofersep.practicaintegradora.entidades.principales;

import com.pablofersep.practicaintegradora.entidades.auxiliares.Auditoria;
import com.pablofersep.practicaintegradora.entidades.auxiliares.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Column
    private String documento;
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Column(name = "telefono_movil")
    private String telefonoMovil;
    @Column
    private String nombre;
    @Embedded
    private Direccion direccion;
    @Column(name = "clase_proveedor")
    private String claseProveedor;
    @Column
    private String comentarios;
    @Embedded
    private Auditoria auditoria;

}
