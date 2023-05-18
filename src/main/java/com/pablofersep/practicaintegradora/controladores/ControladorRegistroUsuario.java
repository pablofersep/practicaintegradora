package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.constantes.Constantes;
import com.pablofersep.practicaintegradora.entidades.auxiliares.Auditoria;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.formobjects.usuario.RegistroUsuario;
import com.pablofersep.practicaintegradora.repositorios.principales.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/registro")
public class ControladorRegistroUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping(value = "/usuario")
    public ModelAndView registrarUsuario(
            HttpServletResponse respuesta,
            HttpServletRequest solicitud
    ){
        ModelAndView mav = new ModelAndView();
        RegistroUsuario registroUsuario = new RegistroUsuario();
        mav.addObject("registroUsuario", registroUsuario);
        mav.addObject("ruta", "/usuario/registro");
        mav.setViewName("registro");
        return mav;
    }
    @PostMapping(value = "/usuario")
    public ModelAndView registrarUsuario(
            @Valid @ModelAttribute("registroUsuario") RegistroUsuario registroUsuario,
            BindingResult errores,
            RedirectAttributes redirect
    ){
        ModelAndView mav = new ModelAndView();
        mav.addObject("ruta", "/usuario/registro");
        mav.addObject("registroUsuario", registroUsuario);
        mav.setViewName("registro");
        if (errores.hasErrors()) return mav;
        if (registroUsuario.getEmail()==null | registroUsuario.getClave()==null | registroUsuario.getConfirmarClave()==null) return mav;

        Auditoria auditoria = new Auditoria();
        auditoria.setFechaAltaEntidad(LocalDateTime.now().toLocalDate());
        auditoria.setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        auditoria.setFechaBorradoEntidad(null);
        auditoria.setFechaFinalBloqueo(Constantes.MIN_MYSQL_DATE);

        Usuario user = new Usuario();
        user.setEmail(registroUsuario.getEmail());
        user.setClave(registroUsuario.getClave());
        user.setNumeroAccesos(0);
        user.setAuditoria(auditoria);

        Usuario comprobacionUser = usuarioRepository.save(user);

        if (comprobacionUser ==  null){
            redirect.addAttribute("mensaje", "Ha habido un error al crear el usuario, intentelo de nuevo por favor");
            return mav;
        }else {
            redirect.addAttribute("mensaje", "Usuario creado correctamente");
        }
        mav.clear();
        mav.setViewName("redirect:/login/usuario");
        return mav;
    }

}
