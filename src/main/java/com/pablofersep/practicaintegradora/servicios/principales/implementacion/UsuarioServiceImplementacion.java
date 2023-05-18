package com.pablofersep.practicaintegradora.servicios.principales.implementacion;

import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.repositorios.principales.UsuarioRepository;
import com.pablofersep.practicaintegradora.servicios.principales.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImplementacion implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findByEmailEquals(String email) {
        return usuarioRepository.findByEmailEquals(email);
    }

    @Override
    public Usuario crear_modificar(Usuario u) {
        try{
            return usuarioRepository.save(u);
        }catch (Exception e){
            return null;
        }
    }
}
