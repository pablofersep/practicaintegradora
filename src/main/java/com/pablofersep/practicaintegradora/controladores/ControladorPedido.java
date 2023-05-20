package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import com.pablofersep.practicaintegradora.entidades.principales.Pedido;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.servicios.principales.PedidoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/pedido")
public class ControladorPedido {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "/listado")
    public ModelAndView listadoCategoria(
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
        mav.addObject("ruta", "/pedido/listado");
        mav.addObject("mensaje", mensaje);
        List<Pedido> pedidos = pedidoService.findAll();
        if (pedidos != null) {
            mav.addObject("pedidos", pedidos);
        }
        if (pedidos.size() == 0) {
            mav.addObject("mensaje", "No existen pedidos en la BBDD");
        }
        return mav;
    }
}
