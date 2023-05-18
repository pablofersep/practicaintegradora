package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.entidades.principales.Promocion;
import com.pablofersep.practicaintegradora.repositorios.principales.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/promocion")
public class ControladorPromocion {

    @Autowired
    private PromocionRepository promocionRepository;

    @GetMapping("/detalle/{id}")
    public ModelAndView detallesPromocion(
            @PathVariable("id") String id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("layout");
        mav.addObject("detalles", true);
        Promocion promocion = promocionRepository.findPromocionById(id);
        if (promocion != null){
            mav.addObject("promocion", promocion);
        }else{
            mav.addObject("noExiste", "Promocion");
        }
        return mav;
    }
}
