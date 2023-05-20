package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import com.pablofersep.practicaintegradora.entidades.principales.Promocion;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.repositorios.principales.PromocionRepository;
import com.pablofersep.practicaintegradora.servicios.principales.PromocionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/promocion")
public class ControladorPromocion {

    @Autowired
    private PromocionService promocionService;

    @GetMapping(value = "/listado")
    public ModelAndView listadoPromocion(
            @RequestParam(required = false) String mensaje,
            HttpSession sesion
    ) {
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
        mav.addObject("ruta", "/promocion/listado");
        mav.addObject("mensaje", mensaje);
        List<Promocion> promociones = promocionService.findAll();
        if (promociones != null) {
            mav.addObject("promociones", promociones);
        }
        if (promociones.size() == 0) {
            mav.addObject("mensaje", "No exiten promociones en la BBDD");
        }
        return mav;
    }

    @GetMapping(value = "/eliminar")
    public ModelAndView eliminarPromociones(
            HttpSession sesion,
            RedirectAttributes redirect
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/promocion/listado");
        Usuario u = (Usuario)sesion.getAttribute("usuario");
        if (u == null){
            return mav;
        }
        List<Promocion>promociones = promocionService.findAll();
        for (Promocion p : promociones){
            //comprobar que la promocion no esta contenida en los catalogos
            promocionService.delete(p);
        }
        return mav;
    }

    /*
    @GetMapping("/detalle/{id}")
    public ModelAndView detallesPromocion(
            @PathVariable("id") String id,
            RedirectAttributes redirect,
            HttpSession sesion
    ) {
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
        mav.addObject("ruta", "/promocion/detalle");
        Promocion promocion = promocionService.findById(Long.valueOf(id));
        if (promocion != null){
            mav.addObject("promocion", promocion);
        }else{
            redirect.addAttribute("mensaje", "No existe producto con codigo " + id);
            mav.setViewName("redirect:/producto/listado");
        }
        return mav;
    }
    */
}
