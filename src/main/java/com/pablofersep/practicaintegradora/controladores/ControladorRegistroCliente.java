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
import com.pablofersep.practicaintegradora.repositorios.datos.*;
import com.pablofersep.practicaintegradora.servicios.datos.TipoClienteService;
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
    private GeneroRepository generoRepository;
    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private TipoViaRepository tipoViaRepository;
    @Autowired
    private TipoDocumentoClienteRepository tipoDocumentoClienteRepository;
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

    private Usuario user;
    private Cliente cliente = new Cliente();
    private RegistroClienteDatosPersonales formObjDatosPersonales = new RegistroClienteDatosPersonales();
    private RegistroClienteDatosContacto formObjDatosContacto = new RegistroClienteDatosContacto();
    private RegistroClienteDatosCliente formObjDatosCliente = new RegistroClienteDatosCliente();

    @ModelAttribute
    public void anadirListas(Model m) {
        m.addAttribute("generos", generoRepository.findAll());
        m.addAttribute("paises", paisRepository.findAll());
        m.addAttribute("vias", tipoViaRepository.findAll());
        m.addAttribute("documentos", tipoDocumentoClienteRepository.findAll());
        m.addAttribute("categorias", categoriaService.findAll());
    }

    @GetMapping(value = "/datos_personales")
    public ModelAndView datosPersonalesClienteGet(
            HttpSession sesion,
            RedirectAttributes redirect
    ) {
        ModelAndView mav = new ModelAndView();
        Usuario u = (Usuario) sesion.getAttribute("usuario");
        if (u == null) {
            redirect.addAttribute("mensaje", "Necesitas logearte para poder registrar un cliente");
            mav.setViewName("redirect:/login/usuario");
            return mav;
        }
        sesion.setAttribute("registro_cliente", "1");
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

        formObjDatosPersonales = formObj;
        cliente.setNombre(formObj.getNombre());
        cliente.setApellidos(formObj.getApellidos());
        cliente.setFechaNacimiento(formObj.getFechaNacimiento());
        cliente.setGenero(formObj.getGeneroSeleccionado());
        cliente.setPaisNacimiento(paisRepository.findPaisBySiglasEquals(formObj.getPaisSeleccionado()));

        sesion.setAttribute("registro_cliente", "2");
        mav.setViewName("redirect:/registro/cliente/datos_contacto");
        return mav;
    }

    @GetMapping(value = "/datos_contacto")
    public ModelAndView datosContactoClienteGet(
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        String step = (String) sesion.getAttribute("registro_cliente");
        if (step == null || step.equals("1")) {
            mav.setViewName("redirect:/registro/cliente/datos_personales");
            return mav;
        }
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
        if (step == null || step.equals("1")) {
            mav.setViewName("redirect:/registro/cliente/datos_personales");
            return mav;
        }
        mav.addObject("datosContacto", formObj);
        mav.addObject("ruta", "/cliente/datos_contacto");
        mav.setViewName("registro");
        if (errores.hasErrors()) return mav;

        formObjDatosContacto = formObj;
        cliente.setTelefonoMovil(formObj.getTelefonoMovil());
        cliente.setTipoDocumentoCliente(tipoDocumentoClienteRepository.findTipoDocumentoClienteBySiglasEquals(formObj.getTipoDocumento()).getNombre());
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
        mav.setViewName("redirect:/registro/cliente/datos_cliente");
        return mav;
    }

    @GetMapping(value = "/datos_cliente")
    public ModelAndView datosClienteGet(
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        String step = (String) sesion.getAttribute("registro_cliente");
        if (step == null || step.equals("1") || step.equals("2")) {
            mav.setViewName("redirect:/registro/cliente/datos_contacto");
            return mav;
        }
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
        if (step == null || step.equals("1") || step.equals("2")) {
            mav.setViewName("redirect:/registro/cliente/datos_contacto");
            return mav;
        }
        mav.addObject("datosCliente", formObj);
        mav.addObject("ruta", "/cliente/datos_cliente");
        mav.setViewName("registro");
        if (errores.hasErrors()) return mav;

        formObjDatosCliente = formObj;
        cliente.setCategoriasInteres(categoriaService.categoriasPorCodigos(formObj.getCategoriasSeleccionadas()));
        cliente.setComentarios(formObj.getComentarios());
        cliente.setAceptacionLicencia(true);

        //ponerle los usuarios a la auditoria

        sesion.setAttribute("registro_cliente", "4");
        mav.setViewName("redirect:/registro/cliente/confirmar_datos");
        return mav;
    }

    @GetMapping(value = "/confirmar_datos")
    public ModelAndView confirmar_datos(
            HttpSession sesion
    ) {
        ModelAndView mav = new ModelAndView();
        String step = (String) sesion.getAttribute("registro_cliente");
        System.out.println(step);
        if (step == null || step.equals("1") || step.equals("2") || step.equals("3")) {
            mav.setViewName("redirect:/registro/cliente/datos_cliente");
            return mav;
        }
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
        if (step == null || step.equals("1") || step.equals("2") || step.equals("3")) {
            mav.setViewName("redirect:/registro/cliente/datos_cliente");
            return mav;
        }
        user = usuarioService.findByEmailEquals(((Usuario) sesion.getAttribute("usuario")).getEmail());
        Auditoria auditoria = new Auditoria();
        auditoria.setFechaAltaEntidad(LocalDateTime.now().toLocalDate());
        auditoria.setFechaUltimaModificacion(LocalDateTime.now().toLocalDate());
        auditoria.setFechaBorradoEntidad(null);
        auditoria.setFechaFinalBloqueo(Constantes.MIN_MYSQL_DATE);

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
        redirect.addAttribute("mensaje", "Creacion de cliente y su carrito correctamente");
        mav.setViewName("redirect:/cliente/listado");
        return mav;
    }

}
