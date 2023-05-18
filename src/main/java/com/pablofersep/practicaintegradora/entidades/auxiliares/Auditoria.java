package com.pablofersep.practicaintegradora.entidades.auxiliares;

import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import javax.security.auth.callback.LanguageCallback;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Auditoria {

    @Column(name = "fecha_alta_entidad")
    private LocalDate fechaAltaEntidad;
    @ManyToOne
    private Usuario usuarioRealizaAlta;
    @Column
    private LocalDate fechaUltimaModificacion;
    @ManyToOne
    private Usuario usuarioRealizaUltimaModificacion;
    @Column
    private LocalDate fechaBorradoEntidad; // == null esta activo
    @ManyToOne
    private Usuario usuarioRealizaBorrado;
    @Column
    private LocalDate fechaFinalBloqueo; // == null no esta bloqueado

}
