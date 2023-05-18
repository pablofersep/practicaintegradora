package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.servicios.principales.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest")
public class RestControler {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/recuperarClave")
    public String recuperarClave(@RequestParam String correo) {
        Usuario usuario = usuarioService.findByEmailEquals(correo);
        return usuario.getClave();
    }

}
