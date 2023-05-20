package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Cliente;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.formobjects.cliente.ConsultaParametrizadaCliente;
import com.pablofersep.practicaintegradora.repositorios.principales.ClientesRepository;
import com.pablofersep.practicaintegradora.servicios.datos.TipoClienteService;
import com.pablofersep.practicaintegradora.servicios.principales.ClientesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/cliente")
public class ControladorCliente {

    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private ClientesService clientesService;
    @Autowired
    private TipoClienteService tipoClienteService;

    @ModelAttribute
    public void anadirListas(Model m) {
        m.addAttribute("tiposCliente", tipoClienteService.findAll());
    }

    @GetMapping(value = "/detalle/{id}")
    public ModelAndView detalleCliente(
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
        mav.addObject("ruta", "/cliente/detalle");
        Cliente cliente = clientesService.findById(id);
        if (cliente != null){
            mav.addObject("cliente", cliente);
        }else{
            redirect.addAttribute("mensaje", "No existe cliente con id " + id);
            mav.setViewName("redirect:/cliente/listado");
        }
        return mav;
    }

    @GetMapping(value = "/listado")
    public ModelAndView listadoClientes(
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
        mav.addObject("ruta", "/cliente/listado");
        mav.addObject("mensaje", mensaje);
        List<Cliente> clientes = clientesService.findAll();
        if (clientes != null){
            mav.addObject("clientes", clientes);
        }if(clientes.size()==0){
            mav.addObject("mensaje", "No existen clientes en la base de datos");
        }
        return mav;
    }
    @PostMapping(value = "/listado")
    public ModelAndView listadoClientes(
            ConsultaParametrizadaCliente formObj,
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
        mav.addObject("ruta", "/cliente/listado");
        List<Cliente> clientes = clientesService.consultaParametrizada(formObj);
        if (clientes != null){
            mav.addObject("clientes", clientes);
        }if (clientes.size()==0){
            mav.addObject("mensaje", "No existen clientes con esos parametros");
        }
        System.out.println(clientes.size()==0);
        return mav;
    }

    @GetMapping(value = "/baja/{id}")
    public ModelAndView bajaUsuario(
            @PathVariable("id") String id,
            RedirectAttributes redirect,
            HttpSession sesion
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/cliente/listado");
        Usuario u = (Usuario)sesion.getAttribute("usuario");
        if (u == null){
            return mav;
        }
        Cliente cliente = clientesRepository.findClienteById(id);
        if (cliente == null){
            redirect.addAttribute("mensaje", "No existe cliente con id " + id);
            return mav;
        }
        cliente.getAuditoria().setFechaBorradoEntidad(LocalDateTime.now().toLocalDate());
        cliente.getAuditoria().setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        Cliente comprobacion = clientesRepository.save(cliente);
        return mav;
    }

    @GetMapping(value = "/alta/{id}")
    public ModelAndView altaUsuario(
            @PathVariable("id") String id,
            RedirectAttributes redirect,
            HttpSession sesion
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/cliente/listado");
        Usuario u = (Usuario)sesion.getAttribute("usuario");
        if (u == null){
            return mav;
        }
        Cliente cliente = clientesRepository.findClienteById(id);
        if (cliente == null){
            redirect.addAttribute("mensaje", "No existe cliente con id " + id);
            return mav;
        }
        cliente.getAuditoria().setFechaBorradoEntidad(null);
        cliente.getAuditoria().setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        Cliente comprobacion = clientesRepository.save(cliente);
        return mav;
    }

}
