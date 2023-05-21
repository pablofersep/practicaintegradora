package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.servicios.principales.UsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/login")
public class ControladorLogin {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/usuario", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView usuario(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String mensaje,
            @CookieValue(defaultValue = "-1") String cookieUsuario,
            HttpServletResponse respuesta,
            HttpServletRequest solicitud,
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mensaje", mensaje);
        sesion.setAttribute("intentos", 0);
        Usuario user;
        user = usuarioService.findByEmailEquals(cookieUsuario);
        if (user != null &&
                cookieUsuario.equals(user.getEmail()) &&
                (user.getAuditoria().getFechaBorradoEntidad() == null &
                        user.getAuditoria().getFechaFinalBloqueo().isBefore(LocalDate.now()))
        ) {
            mav.setViewName("redirect:/login/clave");
            sesion.setAttribute("usuario", user);
            return mav;
        }

        if (usuario != null) {
            user = usuarioService.findByEmailEquals(usuario);
            if (user != null) {
                if (user.getAuditoria().getFechaBorradoEntidad() == null) {
                    if (user.getAuditoria().getFechaFinalBloqueo().isBefore(LocalDate.now())) {
                        Cookie cookie = new Cookie("cookieUsuario", user.getEmail());
                        cookie.setPath("/");
                        respuesta.addCookie(cookie);
                        sesion.setAttribute("usuario", user);
                        mav.setViewName("redirect:/login/clave");
                        return mav;
                    }else {
                        mav.addObject("error", "El usuario se encuentra bloqueado hasta: " + user.getAuditoria().getFechaFinalBloqueo());
                    }
                } else {
                    mav.addObject("error", "Debe introducir un usuario valido");
                }
            } else {
                mav.addObject("error", "Debe introducir un usuario valido");
            }
        }
        mav.addObject("ruta", "/login/usuario");
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "/clave", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView clave(
            @RequestParam(required = false) String clave,
            @CookieValue(defaultValue = "-1") String cookieClave,
            HttpServletResponse respuesta,
            HttpServletRequest solicitud,
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        int intentos = (int)sesion.getAttribute("intentos");
        Usuario user = (Usuario)sesion.getAttribute("usuario");
        if (user == null) {
            mav.setViewName("redirect:/login/usuario");
            return mav;
        }
        mav.addObject("email", user.getEmail());
        mav.addObject("ruta", "/login/clave");
        mav.setViewName("login");

        if (cookieClave.equals(user.getClave())) {
            actualizarUsuario(user);
            mav.setViewName("redirect:http://localhost:5173/?usuario="+user.getEmail());//redireccionar a vue
            sesion.setAttribute("usuario", user);
            return mav;
        }

        if (clave != null) {
            if (clave.equals(user.getClave())) {
                Cookie cookie = new Cookie("cookieClave", clave);
                cookie.setPath("/");
                respuesta.addCookie(cookie);
                actualizarUsuario(user);
                mav.setViewName("redirect:http://localhost:5173/?usuario="+user.getEmail());//redireccionar a vue
                sesion.setAttribute("usuario", user);
                return mav;
            } else {
                intentos++;
                mav.addObject("error", "La pareja usuario-clave no coincide");
            }
        }
        if (intentos == 3) {
            mav.setViewName("redirect:/user/bloqueo/" + user.getEmail());
            return mav;
        }
        return mav;
    }

    @GetMapping(value = "/admin")
    public ModelAndView loginAdmin(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("ruta", "/login/admin");
        mav.setViewName("login");
        return mav;
    }
    @PostMapping(value = "/admin")
    public ModelAndView loginAdminPost(
            @RequestParam String usuario,
            HttpSession sesion
    ){
        ModelAndView mav = new ModelAndView();
        Usuario usuario1 = new Usuario();
        usuario1.setEmail(usuario);
        usuario1.setClave(usuario);
        sesion.setAttribute("usuario", usuario1);
        sesion.setAttribute("conteo", 0);
        mav.setViewName("redirect:/cliente/listado");
        return mav;
    }

    @GetMapping(value = "/desconectar")
    public ModelAndView desconectar(
            HttpSession sesion,
            HttpServletResponse respuesta,
            HttpServletRequest solicitud
    ){
        ModelAndView mav = new ModelAndView();
        sesion.invalidate();
        Cookie[] cookies = solicitud.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("cookieUsuario") | cookie.getName().equals("cookieClave")){
                Cookie temp = new Cookie(cookie.getName(),"");
                temp.setPath("/");
                respuesta.addCookie(temp);
            }
        }
        mav.setViewName("redirect:http://localhost:5173/");
        return mav;
    }
    private void actualizarUsuario(Usuario user) {
        user.setNumeroAccesos(user.getNumeroAccesos() + 1);
        user.setFechaUltimaConexion(LocalDateTime.now().toLocalDate());
        usuarioService.crear_modificar(user);
    }
}
