package com.pablofersep.practicaintegradora.entidades.auxiliares;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column
    private String ubicacion;
    @Column
    private String descripcion;
    @Column
    private Integer tamanio;
    @Column
    private String formato;

}
