package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.constantes.Constantes;
import com.pablofersep.practicaintegradora.entidades.auxiliares.Auditoria;
import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import com.pablofersep.practicaintegradora.formobjects.producto.CreacionProducto;
import com.pablofersep.practicaintegradora.servicios.datos.UrgenciaAvisoService;
import com.pablofersep.practicaintegradora.servicios.principales.AvisoService;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/producto")
public class ControladorProducto {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private AvisoService avisoService;
    @Autowired
    private UrgenciaAvisoService urgenciaAvisoService;

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
            RedirectAttributes redirect,
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
        mav.addObject("ruta", "/producto/modifica");
        mav.addObject("action", "/producto/modificar/"+id);
        Producto producto = productoService.findProductoByCodigo(id);
        CreacionProducto cp = new CreacionProducto();
        if (producto != null) {
            cp.bind(producto);
            mav.addObject("creacionProducto", cp);
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
            @Valid @ModelAttribute("creacionProducto") CreacionProducto formObj,
            BindingResult errores,
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        if (errores.hasErrors()) {
            mav.addObject("creacionProducto", formObj);
            mav.addObject("action", "/producto/modificar/"+id);
            mav.addObject("ruta", "/producto/modifica");
            mav.setViewName("layout");
            return mav;
        }
        if (!id.equals(formObj.getCodigo()) && productoService.findProductoByCodigo(formObj.getCodigo()) != null ){
            mav.addObject("creacionProducto", formObj);
            mav.addObject("action", "/producto/modificar/"+id);
            mav.addObject("ruta", "/producto/modifica");
            mav.addObject("productoExistente", "El producto ya existe en la base de datos");
            mav.setViewName("layout");
            return mav;
        }
        Producto p = productoService.findProductoByCodigo(id);
        if (p == null) {
            redirect.addAttribute("mensaje", "No existe producto con codigo " + id);
            mav.setViewName("redirect:/producto/listado");
            return mav;
        }
        p.bind(formObj, categoriaService);
        p.getAuditoria().setFechaUltimaModificacion(LocalDate.now());
        Producto comprobacion = productoService.crear_modificar(p);
        if (comprobacion == null){
            redirect.addAttribute("mensaje", "No se ha podido crear el producto");
        }else{
            ControladorAviso.creacionAviso(p, productoService, urgenciaAvisoService, avisoService);
            redirect.addAttribute("mensaje", "Modificacion de producto exitosa");
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
        if (productoService.findProductoByCodigo(formObj.getCodigo()) != null){
            mav.addObject("creacionProducto", formObj);
            mav.addObject("ruta", "/producto/alta");
            mav.setViewName("layout");
            mav.addObject("productoExistente", "El producto ya existe en la base de datos");
            return mav;
        }

        Producto p = new Producto();
        p.bind(formObj, categoriaService);

        Auditoria auditoria = new Auditoria();
        auditoria.setFechaAltaEntidad(LocalDateTime.now().toLocalDate());
        auditoria.setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        auditoria.setFechaBorradoEntidad(null);
        auditoria.setFechaFinalBloqueo(Constantes.MIN_MYSQL_DATE);
        //añadir usuario que lo modifica
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
