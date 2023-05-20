package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import com.pablofersep.practicaintegradora.entidades.principales.Proveedor;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.servicios.principales.ProveedorService;
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
@RequestMapping(value = "/proveedor")
public class ControladorProveedor {
    @Autowired
    private ProveedorService proveedorService;
    @GetMapping(value = "/detalle/{id}")
    public ModelAndView detalleProveedor(
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
        mav.addObject("ruta", "/proveedor/detalle");
        Proveedor proveedor = proveedorService.findById(Long.valueOf(id));
        if (proveedor != null) {
            mav.addObject("proveedor", proveedor);
            System.out.println(proveedor.getDireccion().toString());
        } else {
            redirect.addAttribute("mensaje", "No existe proveedor con codigo " + id);
            mav.setViewName("redirect:/proveedor/listado");
        }
        return mav;
    }

    @GetMapping(value = "/listado")
    public ModelAndView listadoProducto(
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
        mav.addObject("ruta", "/proveedor/listado");
        mav.addObject("mensaje", mensaje);
        List<Proveedor> proveedores = proveedorService.findAll();
        if (proveedores != null) {
            mav.addObject("proveedores", proveedores);
        }
        if (proveedores.size() == 0) {
            mav.addObject("mensaje", "No exiten Proveedores en la BBDD");
        }
        return mav;
    }
}
