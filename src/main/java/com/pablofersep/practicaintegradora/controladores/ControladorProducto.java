package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.constantes.Constantes;
import com.pablofersep.practicaintegradora.entidades.auxiliares.Auditoria;
import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import com.pablofersep.practicaintegradora.formobjects.producto.CreacionProducto;
import com.pablofersep.practicaintegradora.servicios.principales.CategoriaService;
import com.pablofersep.practicaintegradora.servicios.principales.ProductoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/producto")
public class ControladorProducto {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @ModelAttribute
    public void anadirListas(Model m) {
        m.addAttribute("categoriasList", categoriaService.findAll());
    }

    @GetMapping(value = "/detalle/{id}")
    public ModelAndView detalleProducto(
            @PathVariable("id") String id,
            RedirectAttributes redirect
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
        mav.addObject("ruta", "/producto/detalle");
        Producto producto = productoService.findProductoByCodigo(id);
        if (producto != null) {
            mav.addObject("producto", producto);
        } else {
            redirect.addAttribute("mensaje", "No existe producto con codigo " + id);
            mav.setViewName("redirect:/producto/listado");
        }
        return mav;
    }

    @GetMapping(value = "/listado")
    public ModelAndView listadoProducto(
            @RequestParam(required = false) String mensaje
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
        mav.addObject("ruta", "/producto/listado");
        mav.addObject("mensaje", mensaje);
        List<Producto> productos = productoService.findAll();
        if (productos != null) {
            mav.addObject("productos", productos);
        }
        if (productos.size() == 0) {
            mav.addObject("mensaje", "No exiten Productos en la BBDD");
        }
        return mav;
    }

    @GetMapping(value = "/modificar/{id}")
    public ModelAndView modificarProducto(
            @PathVariable("id") String id,
            RedirectAttributes redirect
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
        mav.addObject("ruta", "/producto/alta_modifica");
        Producto producto = productoService.findProductoByCodigo(id);
        if (producto != null) {
            mav.addObject("producto", producto);
        } else {
            redirect.addAttribute("mensaje", "No existe producto con codigo " + id);
            mav.setViewName("redirect:/producto/listado");
        }
        return mav;
    }

    @PostMapping(value = "/modificar/{id}")
    public ModelAndView modificarProductoPost(
            @PathVariable("id") String id,
            RedirectAttributes redirect,
            @Valid @ModelAttribute("producto") CreacionProducto formObj,
            BindingResult errores,
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("producto", formObj);
        mav.addObject("ruta", "/modificar/"+id);
        mav.setViewName("layout");
        if (errores.hasErrors()) return mav;

        Producto producto = productoService.findProductoByCodigo(id);
        if (producto != null) {

        } else {
            redirect.addAttribute("mensaje", "No se pudo actualizar el producto con id: " + id);
            mav.setViewName("redirect:/producto/listado");
        }
        mav.setViewName("redirect:/producto/listado");
        return mav;
    }

    @GetMapping(value = "/crear")
    public ModelAndView crearProducto(){
        ModelAndView mav = new ModelAndView();
        CreacionProducto creacionProducto = new CreacionProducto();
        mav.addObject("creacionProducto", creacionProducto);
        mav.addObject("ruta", "/producto/alta");
        mav.setViewName("layout");
        return mav;
    }
    @PostMapping(value = "/crear")
    public ModelAndView crearProductoPost(
            RedirectAttributes redirect,
            @Valid @ModelAttribute("creacionProducto") CreacionProducto formObj,
            BindingResult errores,
            HttpSession sesion
    ){
        ModelAndView mav = new ModelAndView();
        if (errores.hasErrors()){
            mav.addObject("creacionProducto", formObj);
            mav.addObject("ruta", "/producto/alta");
            mav.setViewName("layout");
            return mav;
        }

        Producto p = new Producto();
        p.setCodigo(formObj.getCodigo());
        p.setDescripcion(formObj.getDescripcion());
        p.setPrecio(BigDecimal.valueOf(Float.parseFloat(formObj.getPrecio())));
        p.setCantidadAlmacen(Integer.parseInt(formObj.getCantidadAlmacen()));
        p.setUmbralOcultoEnTienda(Integer.parseInt(formObj.getUmbralOcultoEnTienda()));
        p.setUmbralSolicitudProveedor(Integer.parseInt(formObj.getUmbralSolicitudProveedor()));
        p.setCategorias(categoriaService.categoriasPorCodigos(formObj.getCategorias()));
        p.setEnOferta(formObj.getEnOferta());
        p.setEsNovedad(formObj.getEsNovedad());
        p.setDescuento(BigDecimal.valueOf(Float.parseFloat(formObj.getDescuento())));
        p.setMarca(formObj.getMarca());
        p.setModelo(formObj.getModelo());
        p.setComentarios(formObj.getComentarios());

        Auditoria auditoria = new Auditoria();
        auditoria.setFechaAltaEntidad(LocalDateTime.now().toLocalDate());
        auditoria.setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        auditoria.setFechaBorradoEntidad(null);
        auditoria.setFechaFinalBloqueo(Constantes.MIN_MYSQL_DATE);
        p.setAuditoria(auditoria);

        Producto comprobacion = productoService.crear_modificar(p);
        if (comprobacion == null){
            redirect.addAttribute("mensaje", "No se ha podido crear el producto");
        }else{
            redirect.addAttribute("mensaje", "Creacion de producto exitosa");
        }
        mav.setViewName("redirect:/producto/listado");
        return mav;
    }


}
