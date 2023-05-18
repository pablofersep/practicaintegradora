package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Usuario;

public interface UsuarioService {

    /**
     * @param  email
     * @return Usuario si existe un usuario con ese email. Nulo en caso de que no exista ninguno
     */
    Usuario findByEmailEquals(String email);

    Usuario crear_modificar(Usuario u);
}
