package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Carrito;
import com.pablofersep.practicaintegradora.entidades.principales.CatalogoProveedor;
import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.servicios.principales.CatalogoProveedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/catalogo")
public class ControladorCatalogoProveedor {
    @Autowired
    private CatalogoProveedorService catalogoProveedorService;

    @GetMapping(value = "/listado")
    public ModelAndView listadoCatalogo(
            @RequestParam(required = false) String mensaje,
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
        Usuario u = (Usuario) sesion.getAttribute("usuario");
        if (u == null) {
            mav.addObject("ruta", "noSesion");
            return mav;
        } else {
            sesion.setAttribute("conteo", ((int) sesion.getAttribute("conteo")) + 1);
            mav.addObject("nombreUsuario", u.getEmail());
            mav.addObject("conteo", sesion.getAttribute("conteo"));
        }
        mav.addObject("ruta", "/catalogo/listado");
        mav.addObject("mensaje", mensaje);
        List<CatalogoProveedor> catalogos = catalogoProveedorService.findAll();
        if (catalogos != null) {
            mav.addObject("catalogos", catalogos);
        }
        if (catalogos.size() == 0) {
            mav.addObject("mensaje", "No existen catalogos en la BBDD");
        }
        return mav;
    }
    @GetMapping(value = ("/detalle/{id}"))
    public ModelAndView detallesCatalogo(
            @PathVariable("id") String id,
            RedirectAttributes redirect,
            HttpSession sesion
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
        Usuario u = (Usuario)sesion.getAttribute("usuario");
        if (u == null){
            mav.addObject("ruta", "noSesion");
            return mav;
        }else {
            sesion.setAttribute("conteo", ((int)sesion.getAttribute("conteo"))+1);
            mav.addObject("nombreUsuario", u.getEmail());
            mav.addObject("conteo", sesion.getAttribute("conteo"));
        }
        mav.addObject("ruta", "/catalogo/detalle");
        CatalogoProveedor catalogoProveedor = catalogoProveedorService.findById(Long.valueOf(id));
        if(catalogoProveedor != null){
            mav.addObject("catalogo", catalogoProveedor);
        }else{
            redirect.addAttribute("mensaje", "No existe catalogo con codigo " + id);
            mav.setViewName("redirect:/catalogo/listado");
        }
        return mav;
    }
}
