package com.pablofersep.practicaintegradora.controladores;

import com.pablofersep.practicaintegradora.constantes.Constantes;
import com.pablofersep.practicaintegradora.entidades.auxiliares.Auditoria;
import com.pablofersep.practicaintegradora.entidades.auxiliares.Direccion;
import com.pablofersep.practicaintegradora.entidades.principales.Carrito;
import com.pablofersep.practicaintegradora.entidades.principales.Cliente;
import com.pablofersep.practicaintegradora.entidades.principales.Usuario;
import com.pablofersep.practicaintegradora.formobjects.cliente.RegistroClienteDatosContacto;
import com.pablofersep.practicaintegradora.formobjects.cliente.RegistroClienteDatosPersonales;
import com.pablofersep.practicaintegradora.formobjects.cliente.RegistroClienteDatosCliente;
import com.pablofersep.practicaintegradora.servicios.datos.*;
import com.pablofersep.practicaintegradora.servicios.principales.CarritoService;
import com.pablofersep.practicaintegradora.servicios.principales.CategoriaService;
import com.pablofersep.practicaintegradora.servicios.principales.ClientesService;
import com.pablofersep.practicaintegradora.servicios.principales.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/registro/cliente")
public class ControladorRegistroCliente {
    @Autowired
    private GeneroService generoService;
    @Autowired
    private PaisService paisService;
    @Autowired
    private IdiomaService idiomaService;
    @Autowired
    private TipoViaService tipoViaService;
    @Autowired
    private TipoDocumentoClienteService tipoDocumentoClienteService;
    @Autowired
    private ClientesService clientesService;
    @Autowired
    private TipoClienteService tipoClienteService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CarritoService carritoService;

    @ModelAttribute
    public void anadirListas(Model m) {
        m.addAttribute("generos", generoService.findAll());
        m.addAttribute("paises", paisService.findAll());
        m.addAttribute("idiomas", idiomaService.findAll());
        m.addAttribute("vias", tipoViaService.findAll());
        m.addAttribute("documentos", tipoDocumentoClienteService.findAll());
        m.addAttribute("categorias", categoriaService.findAll());
    }

    @GetMapping(value = "/datos_personales")
    public ModelAndView datosPersonalesClienteGet(
            HttpSession sesion,
            RedirectAttributes redirect
    ) {
        ModelAndView mav = new ModelAndView();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        if (user == null) {
            redirect.addAttribute("mensaje", "Necesitas logearte para poder registrar un cliente");
            mav.setViewName("redirect:/login/usuario");
            return mav;
        }if (usuarioService.findByEmailEquals(user.getEmail()).getCliente()!=null){
            redirect.addAttribute("mensaje", "Esta cuenta ya tiene un cliente registrado");
            mav.setViewName("redirect:/cliente/listado");
            return mav;//Redireccionar A Vue
        }
        sesion.setAttribute("registro_cliente", "1");
        RegistroClienteDatosPersonales formObjDatosPersonales = (RegistroClienteDatosPersonales)sesion.getAttribute("formObjDatosPersonales");
        if (formObjDatosPersonales == null) formObjDatosPersonales = new RegistroClienteDatosPersonales();
        mav.addObject("datosPersonales", formObjDatosPersonales);
        mav.addObject("ruta", "/cliente/datos_personales");
        mav.setViewName("registro");
        return mav;
    }

    @PostMapping(value = "/datos_personales")
    public ModelAndView datosPersonalesClientePost(
            @Valid @ModelAttribute("datosPersonales") RegistroClienteDatosPersonales formObj,
            BindingResult errores,
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("datosPersonales", formObj);
        mav.addObject("ruta", "/cliente/datos_personales");
        mav.setViewName("registro");
        if (errores.hasErrors()) return mav;
        Cliente cliente = new Cliente();
        sesion.setAttribute("formObjDatosPersonales", formObj);
        cliente.setNombre(formObj.getNombre());
        cliente.setApellidos(formObj.getApellidos());
        cliente.setFechaNacimiento(formObj.getFechaNacimiento());
        cliente.setGenero(formObj.getGeneroSeleccionado());
        cliente.setPaisNacimiento(paisService.findBySiglas(formObj.getPaisSeleccionado()));

        sesion.setAttribute("registro_cliente", "2");
        sesion.setAttribute("cliente", cliente);
        mav.setViewName("redirect:/registro/cliente/datos_contacto");
        return mav;
    }

    @GetMapping(value = "/datos_contacto")
    public ModelAndView datosContactoClienteGet(
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        String step = (String) sesion.getAttribute("registro_cliente");
        System.out.println(step);
        if (step == null || step.isEmpty() || step.equals("1")) {
            mav.setViewName("redirect:/registro/cliente/datos_personales");
            return mav;
        }
        RegistroClienteDatosContacto formObjDatosContacto = (RegistroClienteDatosContacto)sesion.getAttribute("formObjDatosContacto");
        if (formObjDatosContacto == null) formObjDatosContacto = new RegistroClienteDatosContacto();
        mav.addObject("datosContacto", formObjDatosContacto);
        mav.addObject("ruta", "/cliente/datos_contacto");
        mav.setViewName("registro");
        return mav;
    }

    @PostMapping(value = "/datos_contacto")
    public ModelAndView datosContactoClienteGetPost(
            @Valid @ModelAttribute("datosContacto") RegistroClienteDatosContacto formObj,
            BindingResult errores,
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        String step = (String) sesion.getAttribute("registro_cliente");
        if (step == null || step.isEmpty() || step.equals("1")) {
            mav.setViewName("redirect:/registro/cliente/datos_personales");
            return mav;
        }
        mav.addObject("datosContacto", formObj);
        mav.addObject("ruta", "/cliente/datos_contacto");
        mav.setViewName("registro");
        if (errores.hasErrors()) return mav;
        Cliente cliente = (Cliente) sesion.getAttribute("cliente");
        sesion.setAttribute("formObjDatosContacto", formObj);
        cliente.setTelefonoMovil(formObj.getTelefonoMovil());
        cliente.setTipoDocumentoCliente(tipoDocumentoClienteService.findBySiglas(formObj.getTipoDocumento()).getNombre());
        cliente.setDocumento(formObj.getDocumento());
        Direccion direccion = new Direccion();
        direccion.setNombre(formObj.getNombreDireccion());
        direccion.setTipoVia(formObj.getTipoVia());
        direccion.setNumero(Integer.parseInt(formObj.getNumero()));
        direccion.setPortal(formObj.getPortal());
        direccion.setPlanta(formObj.getPlanta());
        direccion.setPuerta(formObj.getPuerta());
        direccion.setLocalidad(formObj.getLocalidad());
        direccion.setRegion(formObj.getRegion());
        direccion.setCodigoPostal(formObj.getCodigoPostal());
        cliente.setDireccion(direccion);

        sesion.setAttribute("registro_cliente", "3");
        sesion.setAttribute("cliente", cliente);
        mav.setViewName("redirect:/registro/cliente/datos_cliente");
        return mav;
    }

    @GetMapping(value = "/datos_cliente")
    public ModelAndView datosClienteGet(
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        String step = (String) sesion.getAttribute("registro_cliente");
        if (step == null || step.isEmpty() || step.equals("1") || step.equals("2")) {
            mav.setViewName("redirect:/registro/cliente/datos_contacto");
            return mav;
        }
        RegistroClienteDatosCliente formObjDatosCliente = (RegistroClienteDatosCliente)sesion.getAttribute("formObjDatosCliente");
        if (formObjDatosCliente == null) formObjDatosCliente = new RegistroClienteDatosCliente();
        mav.addObject("datosCliente", formObjDatosCliente);
        mav.addObject("ruta", "/cliente/datos_cliente");
        mav.setViewName("registro");
        return mav;
    }

    @PostMapping(value = "/datos_cliente")
    public ModelAndView datosClientePost(
            @Valid @ModelAttribute("datosCliente") RegistroClienteDatosCliente formObj,
            BindingResult errores,
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        String step = (String) sesion.getAttribute("registro_cliente");
        if (step == null || step.isEmpty() || step.equals("1") || step.equals("2")) {
            mav.setViewName("redirect:/registro/cliente/datos_contacto");
            return mav;
        }
        mav.addObject("datosCliente", formObj);
        mav.addObject("ruta", "/cliente/datos_cliente");
        mav.setViewName("registro");
        if (errores.hasErrors()) return mav;
        Cliente cliente = (Cliente) sesion.getAttribute("cliente");
        sesion.setAttribute("formObjDatosCliente", formObj);
        cliente.setCategoriasInteres(categoriaService.categoriasPorCodigos(formObj.getCategoriasSeleccionadas()));
        cliente.setComentarios(formObj.getComentarios());
        cliente.setAceptacionLicencia(true);

        sesion.setAttribute("registro_cliente", "4");
        sesion.setAttribute("cliente", cliente);
        mav.setViewName("redirect:/registro/cliente/confirmar_datos");
        return mav;
    }

    @GetMapping(value = "/confirmar_datos")
    public ModelAndView confirmar_datos(
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        String step = (String) sesion.getAttribute("registro_cliente");
        if (step == null || step.isEmpty() || step.equals("1") || step.equals("2") || step.equals("3")) {
            mav.setViewName("redirect:/registro/cliente/datos_cliente");
            return mav;
        }
        Cliente cliente = (Cliente) sesion.getAttribute("cliente");
        mav.addObject("cliente", cliente);
        mav.addObject("ruta", "/cliente/confirmar_datos");
        mav.setViewName("registro");
        return mav;
    }

    @PostMapping(value = "/confirmar_datos")
    public ModelAndView confirmar_datos_post(
            HttpSession sesion,
            RedirectAttributes redirect
    ) {
        ModelAndView mav = new ModelAndView();
        String step = (String) sesion.getAttribute("registro_cliente");
        if (step == null || step.isEmpty() || step.equals("1") || step.equals("2") || step.equals("3")) {
            mav.setViewName("redirect:/registro/cliente/datos_cliente");
            return mav;
        }
        Usuario user = usuarioService.findByEmailEquals(((Usuario) sesion.getAttribute("usuario")).getEmail());
        Auditoria auditoria = new Auditoria();
        auditoria.setFechaAltaEntidad(LocalDateTime.now().toLocalDate());
        auditoria.setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        auditoria.setFechaBorradoEntidad(null);
        auditoria.setFechaFinalBloqueo(Constantes.MIN_MYSQL_DATE);

        Cliente cliente = (Cliente) sesion.getAttribute("cliente");
        cliente.setAuditoria(auditoria);
        cliente.setUsuario(user);
        cliente.setId(user.getEmail());
        cliente.setGastoAcumuladoCliente(BigDecimal.valueOf(0));
        cliente.setTipoCliente(tipoClienteService.tipoClienteEnFuncionGasto(cliente));

        Cliente comprobacion1 = clientesService.crear_modificar(cliente);
        if (comprobacion1 == null) {
            redirect.addAttribute("mensaje", "No se ha podido insertar el cliente en la BBDD");
            mav.setViewName("redirect:/cliente/listado");
            return mav;
        }
        Carrito carrito = new Carrito();
        carrito.setFechaCreacion(LocalDate.now());
        carrito.setCliente(comprobacion1);
        Carrito comprobacion2 = carritoService.crear_modificar(carrito);
        if (comprobacion2 == null) {
            redirect.addAttribute("mensaje", "No se ha podido crear el carrito del cliente en la BBDD");
            mav.setViewName("redirect:/cliente/listado");
            return mav;
        }
        sesion.setAttribute("registro_cliente", null);
        redirect.addAttribute("mensaje", "Creacion de cliente y su carrito correctamente");
        mav.setViewName("redirect:/cliente/listado");
        return mav;
    }

}
