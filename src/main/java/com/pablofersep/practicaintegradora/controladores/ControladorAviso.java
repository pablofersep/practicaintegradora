package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.*;
import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import com.pablofersep.practicaintegradora.repositorios.principales.AvisoRepository;
import com.pablofersep.practicaintegradora.repositorios.principales.CategoriaRepository;
import com.pablofersep.practicaintegradora.servicios.datos.UrgenciaAvisoService;
import com.pablofersep.practicaintegradora.servicios.principales.AvisoService;
import com.pablofersep.practicaintegradora.servicios.principales.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/aviso")
public class ControladorAviso {
    @Autowired
    private AvisoService avisoService;

    @GetMapping(value = "/listado")
    public ModelAndView listadoCategoria(
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
        mav.addObject("ruta", "/aviso/listado");
        mav.addObject("mensaje", mensaje);
        List<Aviso> avisos = avisoService.findAll();
        if (avisos != null){
            mav.addObject("avisos", avisos);
        }if (avisos.size()==0){
            mav.addObject("mensaje", "No existen avisos en la BBDD");
        }
        return mav;
    }

    @GetMapping(value = "/procesar/{id}")
    public ModelAndView procesarAviso(
            @PathVariable("id") String id,
            RedirectAttributes redirect,
            HttpSession sesion
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/aviso/listado");
        Usuario u = (Usuario)sesion.getAttribute("usuario");
        if (u == null){
            return mav;
        }
        Aviso aviso = avisoService.findById(Long.parseLong(id));
        if (aviso != null){
            aviso.setFechaProcesado(LocalDate.now());
            Aviso a = avisoService.crear_modificar(aviso);
            if (a != null){
                redirect.addAttribute("mensaje", "Aviso procesado correctamente");
            }if (a == null) {
                redirect.addAttribute("mensaje", "No se ha podido procesar el aviso");
            }
        }if (aviso == null){
            redirect.addAttribute("mensaje", "No se a encontrado el aviso a procesar");
        }
        return mav;
    }

    public static void creacionAviso(Producto p , ProductoService ps, UrgenciaAvisoService us, AvisoService as){
        Aviso a = new Aviso();
        if (p.getCantidadAlmacen() < p.getUmbralSolicitudProveedor()){
            a.setUrgenciaAviso(us.getUrgenciaByCodigo("M"));
            a.setFechaCreacion(LocalDate.now());
            a.setDescripcion("Fecha-"+LocalDate.now()+"-Codigo-"+ p.getCodigo()+": Se rebasa el umbral de solicitud");
            as.crear_modificar(a);
        }if (p.getCantidadAlmacen() < p.getUmbralOcultoEnTienda()) {
            a.setUrgenciaAviso(us.getUrgenciaByCodigo("A"));
            a.setFechaCreacion(LocalDate.now());
            a.setDescripcion("Fecha-"+LocalDate.now()+"-Codigo-"+ p.getCodigo()+": Se rebasa el umbral de ocultamiento");
            p.getAuditoria().setFechaUltimaModificacion(LocalDate.now());
            p.getAuditoria().setFechaBorradoEntidad(LocalDate.now());
            ps.crear_modificar(p);
            as.crear_modificar(a);
        }if (p.getCantidadAlmacen() > p.getUmbralOcultoEnTienda()) {
            p.getAuditoria().setFechaUltimaModificacion(LocalDate.now());
            p.getAuditoria().setFechaBorradoEntidad(null);
            ps.crear_modificar(p);
        }
    }

}
