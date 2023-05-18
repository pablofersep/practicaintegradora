package com.pablofersep.practicaintegradora.entidades.principales;

import com.pablofersep.practicaintegradora.entidades.auxiliares.Auditoria;
import com.pablofersep.practicaintegradora.entidades.auxiliares.Imagen;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Producto {

    @Id
    private String codigo;
    @Column
    private String descripcion;
    @Column
    private BigDecimal precio;
    @Column(name = "unidades_venedidas")
    private Integer unidadesVendidas;
    @Column(name = "gasto_acumulado")
    private BigDecimal gastoAcumulado;
    @ManyToMany
    private Set<Categoria> categorias;
    @Column(name = "cantidad_almacen")
    private Integer cantidadAlmacen;
    @Column(name = "umbral_solicitud_proveedor")
    private Integer umbralSolicitudProveedor;
    @Column(name = "umbral_oculto_tienda")
    private Integer umbralOcultoEnTienda;
    @Column(name = "en_oferta")
    private Boolean enOferta;
    @Column
    private BigDecimal descuento;
    @Column(name = "es_novedad")
    private Boolean esNovedad;
    @Column(name = "valoracion_producto")
    private Integer valoracionProducto;
    @Column
    private String marca;
    @Column
    private String modelo;
    @OneToMany
    private Set<Imagen> imagenes;
    @Column
    private String comentarios;
    @Embedded
    private Auditoria auditoria;

}
