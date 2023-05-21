package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.datos.EstadoPedido;
import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import com.pablofersep.practicaintegradora.entidades.principales.Pedido;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.servicios.datos.EstadoPedidoService;
import com.pablofersep.practicaintegradora.servicios.principales.PedidoService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
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
@RequestMapping(value = "/pedido")
public class ControladorPedido {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private EstadoPedidoService estadoPedidoService;

    @GetMapping(value = "/listado")
    public ModelAndView listadoPedido(
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

    @GetMapping(value = "/siguiente_estado/{id}")
    public ModelAndView siguientePedido(
            @PathVariable("id") String id,
            RedirectAttributes redirect,
            HttpSession sesion
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/pedido/listado");
        Usuario u = (Usuario) sesion.getAttribute("usuario");
        if (u == null) {
            return mav;
        }
        List<EstadoPedido> estadoPedidos = estadoPedidoService.findAll();
        Pedido pedido = pedidoService.findById(id);
        if (pedido == null){
            redirect.addAttribute("mensaje", "No se ha encontrado el pedido");
            return mav;
        }
        if (pedido.getEstadoPedido().equals("Entregado")){
            redirect.addAttribute("mensaje", "Ya se encuentra entregado");
            return mav;
        }
        for (EstadoPedido e : estadoPedidos){
            if (e.getNombre().equals(pedido.getEstadoPedido())){
                pedido.setEstadoPedido(estadoPedidos.get(e.getOrden()).getNombre());
                break;
            }
        }
        Pedido comprobacion = pedidoService.crear_modificar(pedido);
        if (comprobacion == null){
            redirect.addAttribute("mensaje", "No se pudo pasar al siguiente estado");
        }
        redirect.addAttribute("mensaje", "Estado cambiado satisfactoriamente");
        return mav;
    }
}
