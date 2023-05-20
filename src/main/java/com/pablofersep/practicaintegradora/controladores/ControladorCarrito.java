package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Aviso;
import com.pablofersep.practicaintegradora.entidades.principales.Carrito;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.servicios.principales.CarritoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/carrito")
public class ControladorCarrito {
    @Autowired
    private CarritoService carritoService;
    @GetMapping(value = "/listado")
    public ModelAndView listadoCarrito(
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
        mav.addObject("ruta", "/carrito/listado");
        mav.addObject("mensaje", mensaje);
        List<Carrito> carritos = carritoService.findAll();
        if (carritos != null){
            mav.addObject("carritos", carritos);
        }if (carritos.size()==0){
            mav.addObject("mensaje", "No existen carritos en la BBDD");
        }
        return mav;
    }

}
