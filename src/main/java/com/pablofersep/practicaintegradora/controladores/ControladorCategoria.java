package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import com.pablofersep.practicaintegradora.entidades.principales.Cliente;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.repositorios.principales.CategoriaRepository;
import com.pablofersep.practicaintegradora.servicios.principales.CategoriaService;
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
@RequestMapping(value="/categoria")
public class ControladorCategoria {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = ("/detalle/{id}"))
    public ModelAndView detallesCategoria(
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
        mav.addObject("ruta", "/categoria/detalle");
        Categoria categoria = categoriaService.findByCodigo(id);
        if(categoria != null){
            mav.addObject("categoria", categoria);
        }else{
            redirect.addAttribute("mensaje", "No existe la categoria con codigo " + id);
            mav.setViewName("redirect:/categoria/listado");
        }
        return mav;
    }

    @GetMapping(value = "/listado")
    public ModelAndView listadoCategoria(
            @RequestParam(required = false) String mensaje,
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
        mav.addObject("ruta", "/categoria/listado");
        mav.addObject("mensaje", mensaje);
        List<Categoria> categorias = categoriaService.findAll();
        if (categorias != null){
            mav.addObject("categorias", categorias);
        }if (categorias.size()==0){
            mav.addObject("mensaje", "No existen categorias en la BBDD");
        }
        return mav;
    }

}
