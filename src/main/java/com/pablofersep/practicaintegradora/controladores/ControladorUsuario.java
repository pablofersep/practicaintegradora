package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.constantes.Constantes;
import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.repositorios.principales.UsuarioRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/usuario")
public class ControladorUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/detalle/{id}")
    public ModelAndView detalleUsuario(
            @PathVariable("id") String id,
            RedirectAttributes redirect
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
        mav.addObject("ruta", "/usuario/detalle");
        Usuario usuario = usuarioRepository.findByEmailEquals(id);
        if (usuario != null){
            mav.addObject("usuario", usuario);
        }else{
            redirect.addAttribute("mensaje", "No existe producto con codigo " + id);
            mav.setViewName("redirect:/usuario/listado");
        }
        return mav;
    }

    @GetMapping(value = "/listado")
    public ModelAndView listadoUsuario(
            @RequestParam(required = false) String mensaje,
            HttpSession sesion
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
        Usuario u = (Usuario)sesion.getAttribute("admin");
        if (u == null){
            mav.addObject("ruta", "noSesion");
            return mav;
        }else {
            sesion.setAttribute("conteo", ((int)sesion.getAttribute("conteo"))+1);
        }
        mav.addObject("ruta", "/usuario/listado");
        mav.addObject("mensaje", mensaje);
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios != null){
            mav.addObject("usuarios", usuarios);
        }if (usuarios.size() == 0){
            mav.addObject("mensaje", "No exiten Usuarios en la BBDD");
        }
        return mav;
    }

    @GetMapping(value = "/baja/{id}")
    public ModelAndView bajaUsuario(
            @PathVariable("id") String id,
            RedirectAttributes redirect
    ){
        ModelAndView mav = new ModelAndView();
        Usuario user = usuarioRepository.findByEmailEquals(id);
        user.getAuditoria().setFechaBorradoEntidad(LocalDateTime.now().toLocalDate());
        user.getAuditoria().setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        Usuario comprobacion = usuarioRepository.save(user);
        if (comprobacion==null){
            redirect.addAttribute("mensaje", "Baja inexitoso del usuario " + id);
        }else {
            redirect.addAttribute("mensaje", "Baja exitoso del usuario " + id);
        }
        mav.setViewName("redirect:/usuario/listado");
        return mav;
    }

    @GetMapping(value = "/alta/{id}")
    public ModelAndView rehabilitarUsuario(
            @PathVariable("id") String id,
            RedirectAttributes redirect
    ){
        ModelAndView mav = new ModelAndView();
        Usuario user = usuarioRepository.findByEmailEquals(id);
        user.getAuditoria().setFechaBorradoEntidad(null);
        user.getAuditoria().setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        Usuario comprobacion = usuarioRepository.save(user);
        if (comprobacion==null){
            redirect.addAttribute("mensaje", "Alta inexitoso del usuario " + id);
        }else {
            redirect.addAttribute("mensaje", "Alta exitoso del usuario " + id);
        }
        mav.setViewName("redirect:/usuario/listado");
        return mav;
    }

    @GetMapping(value = "/bloqueo/{id}")
    public ModelAndView bloqueoUsuario(
            @PathVariable("id") String id,
            RedirectAttributes redirect,
            HttpServletResponse respuesta,
            HttpServletRequest solicitud
    ){
        ModelAndView mav = new ModelAndView();
        Usuario user = usuarioRepository.findByEmailEquals(id);
        user.getAuditoria().setFechaFinalBloqueo(LocalDateTime.now().toLocalDate().plusDays(1));
        user.getAuditoria().setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        Usuario comprobacion = usuarioRepository.save(user);
        if (comprobacion==null){
            redirect.addAttribute("mensaje", "Bloqueo inexitoso del usuario " + id);
        }else {
            redirect.addAttribute("mensaje", "Bloqueo exitoso del usuario " + id);
        }
        Cookie cookie = new Cookie("usuario", "");
        respuesta.addCookie(cookie);
        mav.setViewName("redirect:/login/usuario");
        return mav;
    }

    //Este metodo no tendria ni que existir
    //O tendria que estar subido en un servidor privado para que no sea accesible desde el exterior
    @GetMapping(value = "/desbloqueo/{id}")
    public ModelAndView desbloqueoUsuario(
            @PathVariable("id") String id,
            RedirectAttributes redirect,
            HttpServletResponse respuesta,
            HttpServletRequest solicitud
    ){
        ModelAndView mav = new ModelAndView();
        Usuario user = usuarioRepository.findByEmailEquals(id);
        user.getAuditoria().setFechaFinalBloqueo(Constantes.MIN_MYSQL_DATE);
        user.getAuditoria().setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        Usuario comprobacion = usuarioRepository.save(user);
        if (comprobacion==null){
            redirect.addAttribute("mensaje", "Desbloqueo inexitoso del usuario " + id);
        }else {
            redirect.addAttribute("mensaje", "Desbloqueo exitoso del usuario " + id);
        }
        mav.setViewName("redirect:/usuario/listado");
        return mav;
    }

}