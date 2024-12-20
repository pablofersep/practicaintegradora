package com.pablofersep.practicaintegradora.formobjects.producto;

import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import com.pablofersep.practicaintegradora.validaciones.CategoriasExisten;
import com.pablofersep.practicaintegradora.validaciones.ProductoExistente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreacionProducto {

    @NotNull
    @NotBlank
    private String codigo;
    @NotNull
    @NotBlank
    private String descripcion;
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9]{1,}\\.[0-9]{2}")
    private String precio;
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9]{1,}")
    private String cantidadAlmacen;
    @Pattern(regexp = "[0-9]{1,}")
    private String umbralSolicitudProveedor;
    @Pattern(regexp = "[0-9]{1,}")
    private String umbralOcultoEnTienda;
    @CategoriasExisten
    private List<String> categorias;
    private Boolean enOferta;
    private Boolean esNovedad;
    @Pattern(regexp = "[0-9]{1,}\\.[0-9]{2}")
    private String descuento;
    @NotNull
    @NotBlank
    private String marca;
    @NotNull
    @NotBlank
    private String modelo;
    private String comentarios;

    public void bind(Producto p){
        setCodigo(p.getCodigo());
        setDescripcion(p.getDescripcion());
        setPrecio(String.valueOf(p.getPrecio()));
        setCantidadAlmacen(String.valueOf(p.getCantidadAlmacen()));
        setUmbralOcultoEnTienda(String.valueOf(p.getUmbralOcultoEnTienda()));
        setUmbralSolicitudProveedor(String.valueOf(p.getUmbralSolicitudProveedor()));
        setCategorias(new ArrayList<String>());
        for (Categoria c : p.getCategorias()){
            getCategorias().add(c.getCodigo());
        }
        setEnOferta(p.getEnOferta());
        setEsNovedad(p.getEsNovedad());
        setDescuento(String.valueOf(p.getDescuento()));
        setMarca(p.getMarca());
        setModelo(p.getModelo());
        setComentarios(p.getComentarios());
    }
}
