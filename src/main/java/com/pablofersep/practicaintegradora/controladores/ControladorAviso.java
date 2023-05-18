package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Aviso;
import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import com.pablofersep.practicaintegradora.formobjects.aviso.ConsultaAviso;
import com.pablofersep.practicaintegradora.repositorios.principales.AvisoRepository;
import com.pablofersep.practicaintegradora.repositorios.principales.CategoriaRepository;
import com.pablofersep.practicaintegradora.servicios.datos.UrgenciaAvisoService;
import com.pablofersep.practicaintegradora.servicios.principales.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/aviso")
public class ControladorAviso {
    @Autowired
    private AvisoService avisoService;
    @Autowired
    private UrgenciaAvisoService urgenciaAvisoService;
    @ModelAttribute
    public void anadirListas(Model m) {
        m.addAttribute("urgenciaAvisos", urgenciaAvisoService.findAll());
    }

    @GetMapping(value = "/listado")
    public ModelAndView listadoCategoria(
            @RequestParam(required = false) String mensaje
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
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
    @PostMapping(value = "/listado")
    public ModelAndView listadoCategoriaPost(
            @RequestParam(required = false) String mensaje,
            ConsultaAviso formObj
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
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
            RedirectAttributes redirect
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/aviso/listado");
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

    public void creacionAviso(){

    }

}
